package mk.ukim.finki.lab7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.stream.Collectors;


class NoSuchRoomException extends Exception {
    public NoSuchRoomException(String message) {
        super(message);
    }
}

class NoSuchUserException extends Exception {
    public NoSuchUserException(String message) {
        super(message);
    }
}

class ChatRoom {
    String roomName;
    TreeSet<String> users;


    public ChatRoom(String roomName) {
        this.roomName = roomName;
        users = new TreeSet<>();
    }


    public void addUser(String user) {
        users.add(user);
    }

    public void removeUser(String user) {
        users.remove(user);
    }

    public boolean hasUser(String user) {
        return users.contains(user);
    }

    public int numOfUsers() {
        return users.size();
    }

    public String getRoomName() {
        return roomName;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(roomName).append("\n");
        if (users.isEmpty())
            str.append("EMPTY").append("\n");
        else
            users.forEach(u -> str.append(u).append("\n"));

        return str.toString();
    }
}

class ChatSystem {
    TreeMap<String, ChatRoom> rooms;

    List<String> registeredUsers;


    public ChatSystem() {
        rooms = new TreeMap<>();
        registeredUsers = new ArrayList<>();
    }

    public void register(String userName) {
        registeredUsers.add(userName);
        if (!rooms.isEmpty()) {
            List<ChatRoom> sorted = rooms
                    .values()
                    .stream()
                    .sorted(Comparator.comparing(ChatRoom::numOfUsers).thenComparing(ChatRoom::getRoomName))
                    .collect(Collectors.toList());
            sorted.get(0).users.add(userName);
        }
    }

    public void registerAndJoin(String userName, String roomName) {
        registeredUsers.add(userName);
        rooms.get(roomName).users.add(userName);
    }

    public void joinRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if (!rooms.containsKey(roomName)) throw new NoSuchRoomException(roomName);
        if (!registeredUsers.contains(userName)) throw new NoSuchUserException(userName);

        rooms.get(roomName).users.add(userName);
    }

    public void leaveRoom(String username, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if (!rooms.containsKey(roomName)) throw new NoSuchRoomException(roomName);
        if (!registeredUsers.contains(username)) throw new NoSuchUserException(username);

        rooms.get(roomName).removeUser(username);
    }

    public void followFriend(String username, String friend_username) {
        rooms
                .values()
                .stream()
                .filter(r -> r.users.contains(friend_username))
                .forEach(i -> i.users.add(username));
    }

    public void addRoom(String roomName) {
        rooms.put(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName) {
        rooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if (!rooms.containsKey(roomName)) throw new NoSuchRoomException(roomName);

        return rooms.get(roomName);
    }
}

public class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for (int i = 0; i < n; ++i) {
                k = jin.nextInt();
                if (k == 0) cr.addUser(jin.next());
                if (k == 1) cr.removeUser(jin.next());
                if (k == 2) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if (n == 0) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for (int i = 0; i < n; ++i) {
                k = jin.nextInt();
                if (k == 0) cr2.addUser(jin.next());
                if (k == 1) cr2.removeUser(jin.next());
                if (k == 2) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if (k == 1) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while (true) {
                String cmd = jin.next();
                if (cmd.equals("stop")) break;
                if (cmd.equals("print")) {
                    System.out.println(cs.getRoom(jin.next()) + "\n");
                    continue;
                }
                for (Method m : mts) {
                    if (m.getName().equals(cmd)) {
                        Object params[] = new String[m.getParameterTypes().length];
                        for (int i = 0; i < params.length; ++i) params[i] = jin.next();
                        m.invoke(cs, params);
                    }
                }
            }
        }
    }

}
