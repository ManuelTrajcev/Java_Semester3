package algoritmi.prvKolokvium;

import java.util.Scanner;
import java.util.Stack;


//Дадена е апсолутната патека до некој директориум или датотека во модифициран Unix датотечен систем. Системот функционира на следниот начин:
//
//        "." го претставува моменталниот директориум
//        ".k" каде што k е позитивен цел број го претставува директориумот k нивоа пред моменталнот. Пример: патеката /a/b/c/.1 води до /a/b, а патеката /a/b/c/.2 до /a. Големината на k не е ограничена, бројот може да има и повеќе од една цифра.
//        имињата на директориумите и датотеките не почнуваат со точка (.)
//        сите останати формати кои содржат карактер точка (.) се имиња на датотеки или директориуми
//        секогаш кога има повеќе коси црти "//" тие се третираат како една "/"
//        "/" е највисокото ниво и не се излегува од него
//        Ваша задача е да ја вратите поедноставената канонична патека за која важи:
//
//        патеката содржи само имиња на директориуми од почетокот до крајниот директориум или датотека
//        не содржи "." или ".k" каде што k е позитивен цел број
//        помеѓу секои два директориуми или директориум и датотека има само една коса црта
//        почнува со коса црта "/"
//        не завршува со коса црта
//        Влез: Почетната (апсолутна) патека.
//
//        Излез: Поедноставената канонична патека.
//
//        Пример:
//
//        Влез:
//
//        /abc///cde/xyz/.1/asdf//a1.b2/newdir/.2/a/
//
//        Излез:
//
//        /abc/cde/asdf/a

public class ModifiedUnixPath {

    public static String simplifyPath(String path) {
        Stack<Character> str = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        str.push(path.charAt(0));
        int j = 0;
        for (int i = 1; i < path.length(); i++) {
            if (str.peek() == '/' && path.charAt(i) == '/') {
                continue;
            }
            if (path.charAt(i) == '.') {
                int n = path.length();
                if ((i + 2) >= path.length() || path.charAt(i + 2) == '/') {        // ||path.charAt(i + 2) == '\t')
                    int c = Integer.parseInt(String.valueOf(path.charAt(i + 1)));
                    str.pop();
                    while (c > 0) {
                        str.pop();
                        if (str.peek() == '/') {
                            c--;
                        }
                    }
                    i += 3;
                } else if (path.charAt(i + 1) == '/') {
                    j = i;
                    while (path.charAt(j) != '/') {
                        j++;
                    }
                    if (j + 1 == path.length()) {
                        continue;
                    } else {
                        while (str.peek() != '/') {
                            str.pop();
                        }
                        i = j + 1;
                    }
                }

//
            }
            if (i >= path.length()) {
                break;
            }
            if (str.peek() == '/' && path.charAt(i) == '/') continue;
            str.push(path.charAt(i));
        }

        if (str.peek() == '/')
            str.pop();

        Stack<Character> flipped = new Stack<>();

        while (!str.isEmpty()) {
            flipped.push(str.pop());
        }
        while (!flipped.isEmpty()) {
            stringBuilder.append(flipped.pop());
        }

//        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String path = input.nextLine();

        input.close();

        simplifyPath(path);

        System.out.println(simplifyPath(path));
    }
}
