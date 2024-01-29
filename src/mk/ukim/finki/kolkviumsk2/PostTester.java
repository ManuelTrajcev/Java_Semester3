package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Comment {
    String author;
    String id;
    String content;
    List<Comment> comments;
    int likes;


    public Comment(String author, String id, String content) {
        this.author = author;
        this.id = id;
        this.content = content;
        this.comments = new ArrayList<>();
        this.likes = 0;
    }

    public void writeComment(String author, String id, String content, String replyToId) {
        if (replyToId.equals(this.id)) {
            comments.add(new Comment(author, id, content));
        } else {
            comments
                    .forEach(c -> c.writeComment(author, id, content, replyToId));
        }
    }

    public void likeComment(String commentID) {
        if (this.id.equals(commentID)) {
            likes++;
        } else {
            comments.forEach(c -> c.likeComment(commentID));
        }
    }

    public int totalLikes() {
        return likes + comments.stream().mapToInt(Comment::totalLikes).sum();
    }

    public String getComment(int i) {
        String indent = " ".repeat(i);
        comments.sort(Comparator.comparing(Comment::totalLikes).reversed());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(indent).append("Comment: ").append(content).append("\n");
        stringBuilder.append(indent).append("Written by: ").append(author).append("\n");
        stringBuilder.append(indent).append("Likes: ").append(likes).append("\n");
        comments.forEach(c -> stringBuilder.append(c.getComment(i + 4)));
        return stringBuilder.toString();
    }
}

class Post {
    String username;
    String postContent;
    HashMap<String, Comment> comments;

    public Post(String username, String postContent) {
        this.username = username;
        this.postContent = postContent;
        this.comments = new LinkedHashMap<>();
    }

    public void addComment(String author, String id, String content, String replyToId) {
        if (replyToId == null) {
            comments.put(id, new Comment(author, id, content));
        } else {
            comments
                    .values()
                    .forEach(c -> c.writeComment(author, id, content, replyToId));
        }
    }

    public void likeComment(String commentID) {
        comments
                .values()
                .forEach(c -> c.likeComment(commentID));

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Post: ").append(postContent).append("\n");
        stringBuilder.append("Written by: ").append(username).append("\n");
        stringBuilder.append("Comments:").append("\n");
        comments.values()
                .stream().sorted(Comparator.comparing(Comment::totalLikes).reversed())
                .forEach(c -> stringBuilder.append(c.getComment(8)));
        stringBuilder.toString();
        return stringBuilder.toString();
    }
}

public class PostTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postAuthor = sc.nextLine();
        String postContent = sc.nextLine();

        Post p = new Post(postAuthor, postContent);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String testCase = parts[0];

            if (testCase.equals("addComment")) {
                String author = parts[1];
                String id = parts[2];
                String content = parts[3];
                String replyToId = null;
                if (parts.length == 5) {
                    replyToId = parts[4];
                }
                p.addComment(author, id, content, replyToId);
            } else if (testCase.equals("likes")) { //likes;1;2;3;4;1;1;1;1;1 example
                for (int i = 1; i < parts.length; i++) {
                    p.likeComment(parts[i]);
                }
            } else {
                System.out.println(p);
            }

        }
    }
}
