package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;

interface DisplayStrategy {
    void sortLogsInMicroService(List<Log> logs);

}

class NewestFirstStrategy implements DisplayStrategy {

    @Override
    public void sortLogsInMicroService(List<Log> logs) {
        logs.sort(Comparator.comparing(Log::getTimestamp).reversed());
    }
}

class OldestFirstStrategy implements DisplayStrategy {

    @Override
    public void sortLogsInMicroService(List<Log> logs) {
        logs.sort(Comparator.comparing(Log::getTimestamp));
    }
}

class MostSevereFirstStrategy implements DisplayStrategy {

    @Override
    public void sortLogsInMicroService(List<Log> logs) {
        logs.sort(Comparator.comparing(Log::totalSeverity).thenComparing(Log::getTimestamp).reversed());
    }
}

class LeastSevereFirstStrategy implements DisplayStrategy {

    @Override
    public void sortLogsInMicroService(List<Log> logs) {
        logs.sort(Comparator.comparing(Log::totalSeverity));
    }
}

class Log {

    String type;
    String message;
    int timestamp;
    int severity;
    String microService;

    public Log(String type, String message, int timestamp, String microService) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.severity = totalSeverity();
        this.microService = microService;
    }

    public int totalSeverity() {
        int total = 0;
        if (type.equals("WARN")) {
            total += 1;
            if (message.contains("might cause error")) {
                total += 1;
            }
        } else if (type.equals("ERROR")) {
            total += 3;
            if (message.contains("fatal")) {
                total += 2;
            }
            if (message.contains("exception")) {
                total += 3;
            }
        }
        return total;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] %s%s T:%d", microService, type, message, timestamp, timestamp);
    }
}

class MicroService {
    String microServiceName;
    List<Log> logs;
    HashMap<Integer, List<Log>> logMap;

    public MicroService(String microServiceName) {
        this.microServiceName = microServiceName;
        this.logs = new ArrayList<>();
        this.logMap = new HashMap<>();
    }

    public void addLog(String line) {
        String[] parts = line.split("\\s+");
        String type = parts[2];
        String message = getMessageFromArray(parts);
        int timestamp = Integer.parseInt(parts[parts.length - 1]);
        Log newLog = new Log(type, message, timestamp, microServiceName);
        logs.add(newLog);
        if (logMap.containsKey(newLog.totalSeverity())) {
            logMap.get(newLog.totalSeverity()).add(newLog);
        } else {
            List<Log> tmp = new ArrayList<>();
            tmp.add(newLog);
            logMap.put(newLog.totalSeverity(), tmp);
        }
    }

    private String getMessageFromArray(String[] parts) {
        StringBuilder str = new StringBuilder();
        for (int i = 3; i < parts.length - 1; i++) {
            str.append(parts[i]).append(" ");
        }
        return str.toString();
    }

    public int logCount() {
        return logs.size();
    }

    public double getAvgSeverityPerMicroService() {
        int totalSeverity = logs.stream().mapToInt(Log::totalSeverity).sum();
        double avg = logs.stream().mapToDouble(Log::totalSeverity).sum() / logCount();
        return avg;
    }

    public double getTotalSeverityPerMicroSystem() {
        double sum = logs.stream().mapToDouble(Log::totalSeverity).sum();
        return sum;
    }

    public double getLogNumPerMicroService() {
        return logs.size();
    }

    public List<Log> getLogs() {
        return logs;
    }
}

class Service {
    String serviceName;
    HashMap<String, MicroService> microServices;

    public Service(String serviceName) {
        this.serviceName = serviceName;
        microServices = new HashMap<>();
    }

    public void addMicroService(String line) {
        String[] parts = line.split("\\s+");
        if (microServices.containsKey(parts[1])) {
            microServices.get(parts[1]).addLog(line);
        } else {
            microServices.put(parts[1], new MicroService(parts[1]));
            microServices.get(parts[1]).addLog(line);
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getMicroServicesCount() {
        return microServices.entrySet().size();
    }

    public int getTotalLogCount() {
        return microServices.values().stream().mapToInt(MicroService::logCount).sum();
    }

    public double getAverageSeverity() {
        double sum = microServices.values().stream().mapToDouble(MicroService::getTotalSeverityPerMicroSystem).sum();
        double total = microServices.values().stream().mapToDouble(i -> i.logCount()).sum();
        return sum / total;
    }

    public double getAvgNumLogs() {
        return microServices.values().stream().mapToDouble(MicroService::getLogNumPerMicroService).sum() / microServices.size();
    }
}

class LogCollector {
    HashMap<String, Service> services;
    DisplayStrategy displayStrategy;


    public LogCollector() {
        this.services = new HashMap<>();
    }

    public void addLog(String line) {
        String[] parts = line.split("\\s+");
        if (services.containsKey(parts[0])) {
            services.get(parts[0]).addMicroService(line);
        } else {
            services.put(parts[0], new Service(parts[0]));
            services.get(parts[0]).addMicroService(line);
        }
    }

    public void printServicesBySeverity() {
        List<Service> sorted = services.values().stream().sorted(Comparator.comparing(Service::getAverageSeverity)).collect(Collectors.toList());
        sorted.sort(Comparator.comparing(Service::getAverageSeverity).reversed());
        sorted.forEach(s -> {
            System.out.println(String.format("Service name: %s Count of microservices: %d Total logs in service: %d Average severity for all logs: %.2f Average number of logs per microservice: %.2f",
                    s.getServiceName(), s.getMicroServicesCount(), s.getTotalLogCount(), s.getAverageSeverity(), s.getAvgNumLogs()));
            ;
        });

    }

    public Map<Integer, Integer> getSeverityDistribution(String service, String microservice) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (services.containsKey(service)) {
            if (microservice != null && services.get(service).microServices.containsKey(microservice)) {
                services.get(service).microServices.get(microservice).logMap.forEach((key, value) ->
                        map.merge(key, value.size(), Integer::sum)
                );
            } else {
                services.get(service).microServices.values().forEach(microService ->
                        microService.logMap.forEach((key, value) ->
                                map.merge(key, value.size(), Integer::sum)
                        )
                );
            }
        }
        return map;
    }


    void displayLogs(String service, String microservice, String order) {
        List<Log> toDisplay = new ArrayList<>();
        switch (order) {
            case "NEWEST_FIRST":
                displayStrategy = new NewestFirstStrategy();
                break;
            case "OLDEST_FIRST":
                displayStrategy = new OldestFirstStrategy();
                break;
            case "MOST_SEVERE_FIRST":
                displayStrategy = new MostSevereFirstStrategy();
                break;
            case "LEAST_SEVERE_FIRST":
                displayStrategy = new LeastSevereFirstStrategy();
                break;
        }
        if (microservice != null) {
            toDisplay = services.get(service).microServices.get(microservice).logs.stream().collect(Collectors.toList());
        } else {
            toDisplay = services.get(service).microServices.entrySet().stream()
                    .flatMap(i -> i.getValue().getLogs().stream())
                    .collect(Collectors.toList());
        }
        displayStrategy.sortLogsInMicroService(toDisplay);
        toDisplay.forEach(l ->
                System.out.println(String.format("%s|%s", service, l)));
    }
}

public class LogsTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LogCollector collector = new LogCollector();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.startsWith("addLog")) {
                collector.addLog(line.replace("addLog ", ""));
            } else if (line.startsWith("printServicesBySeverity")) {
                collector.printServicesBySeverity();
            } else if (line.startsWith("getSeverityDistribution")) {
                String[] parts = line.split("\\s+");
                String service = parts[1];
                String microservice = null;
                if (parts.length == 3) {
                    microservice = parts[2];
                }
                collector.getSeverityDistribution(service, microservice).forEach((k, v) -> System.out.printf("%d -> %d%n", k, v));
            } else if (line.startsWith("displayLogs")) {
                String[] parts = line.split("\\s+");
                String service = parts[1];
                String microservice = null;
                String order = null;
                if (parts.length == 4) {
                    microservice = parts[2];
                    order = parts[3];
                } else {
                    order = parts[2];
                }
                System.out.println(line);

                collector.displayLogs(service, microservice, order);
            }
        }
    }
}