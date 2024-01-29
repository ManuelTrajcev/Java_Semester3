package mk.ukim.finki.kolokviumski;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

class Category {
    String name;

    public Category(String name) {
        this.name = name;
    }

}

abstract class NewsItem {
    String title;
    Date dateOfPublishing;
    Category category;

    public NewsItem(String title, Date dateOfPublishing, Category category) {
        this.title = title;
        this.dateOfPublishing = dateOfPublishing;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    abstract String getTeaser();
}

class TextNewsItem extends NewsItem {
    String text;

    public TextNewsItem(String title, Date dateOfPublishing, Category category, String text) {
        super(title, dateOfPublishing, category);
        this.text = text;
    }

    @Override
    String getTeaser() {
        Instant now = Instant.now();
        Instant past = dateOfPublishing.toInstant();
        long duration = Duration.between(past, now).toMinutes();
        return String.format("%s\n%d\n%s\n", title, duration, text.length() > 80 ? text.substring(0, 80) : text);
    }

    @Override
    public String toString() {
        return getTeaser();
    }
}

class MediaNewsItem extends NewsItem {
    String url;
    int views;

    public MediaNewsItem(String title, Date dateOfPublishing, Category category, String url, int views) {
        super(title, dateOfPublishing, category);
        this.url = url;
        this.views = views;
    }

    @Override
    String getTeaser() {
        Instant now = Instant.now();
        Instant past = dateOfPublishing.toInstant();
        long duration = Duration.between(past, now).toMinutes();
        return String.format("%s\n%d\n%s\n%d\n", title, duration, url, views);
    }

    @Override
    public String toString() {
        return getTeaser();
    }
}

class FrontPage {
    List<NewsItem> news;
    Category[] categories;

    public FrontPage(Category[] categories) {
        this.news = new ArrayList<>();
        this.categories = categories;
    }

    void addNewsItem(NewsItem newsItem) {
        news.add(newsItem);
    }

    List<NewsItem> listByCategory(Category category) {
        return news.stream().filter(n -> n.category.equals(category)).collect(Collectors.toList());
    }

    List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        boolean notFound = true;
        for (Category c : categories) {
            if (category.equals(c.name)) {
                notFound = false;
                break;
            }
        }
        if (notFound) {
            throw new CategoryNotFoundException(String.format("Category " + category + " was not found"));
        }
        return news.stream().filter(n -> n.category.name.equals(category)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        news.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}


public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for (Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


// Vasiot kod ovde