package mk.ukim.finki.av11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
<student gender="Male" age=26></student>    - prost tag LEAF
<student gender="Male" age=26>
    <name>
    <first_name>Stefan</first_name>
    <last_name>Andonov</last_name>
    </name>
</student>    - slozen tag      COMPOSITE


 */

interface XMLComponent {        //indefifikuvaj razliki od leaf i composite
    String print(int indent);

    void addAttribute(String type, String redoven);
}

abstract class XMLElement implements XMLComponent {
    String tagName;
    Map<String, String> attributes;

    public XMLElement(String tagName) {
        this.tagName = tagName;
        attributes = new LinkedHashMap<>(); //linke -> se zacuvuva redosledo
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }
}

class XMLLeaf extends XMLElement {
    String value;

    public XMLLeaf(String tagName, String value) {
        super(tagName);
        this.value = value;
    }

    @Override
    //student type="redoven" program="KNI">Trajce Trajkovski</student>
    public String print(int indent) {
        return String.format("<%s %s>%s</%s>", tagName, attributes.entrySet().
                stream().
                map(entry -> String.format("%s=\"%s\"", entry.getKey(), entry.getValue())), tagName);
    }
}

class XMLComposite extends XMLElement {
    List<XMLComponent> children;

    public XMLComposite(String tagName) {
        super(tagName);
        children = new ArrayList<>();
    }


    public void addComponent(XMLComponent child) {
        children.add(child);
    }

    @Override
    //student type="redoven" program="KNI">Trajce Trajkovski</student>
    public String print(int indent) {
        return String.format("<%s %s>\n%s\n</%s>",
                IntStream.range(0, indent).mapToObj(i -> "\t").collect(Collectors.joining("")),
                tagName, attributes.entrySet().
                        stream().
                        map(entry -> String.format("%s=\"%s\"", entry.getKey(), entry.getValue())),
                children.stream().map(child -> child.print(indent))
                , tagName);
    }
}

public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase == 1) {
            component.print(0);
        } else if (testCase == 2) {
            //TODO print the composite object
        } else if (testCase == 3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level", "1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level", "2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level", "3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            //TODO print the main object
        }
    }
}
