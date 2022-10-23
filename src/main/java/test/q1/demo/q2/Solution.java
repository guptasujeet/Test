package test.q1.demo.q2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Result {

    static class Node {
        public int person;
        public Set<Node> knownPersons = new HashSet<>();

        public Node(int person) {
            this.person = person;
        }

        public void addKnownPerson(Node otherPerson) {
            knownPersons.add(otherPerson);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return person == node.person;
        }

        @Override
        public int hashCode() {
            return Objects.hash(person);
        }
    }

    public static int countGroups(List<String> related) {
        // Write your code here
        int size = related.size();
        boolean[][] matrix = getMatrix(size, related);

        Map<Integer, Node> personIdentity = new ConcurrentHashMap<>();
        for (int i = 0; i < size; i++) {
            personIdentity.put(i, new Node(i));
        }

        for (int i = 0; i < size; i++) {
            Node person = personIdentity.get(i);
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == true && i != j) {
                    person.addKnownPerson(personIdentity.get(j));
                }
            }
        }

        for (Integer person : personIdentity.keySet()) {
            Set<Node> knownPersons = personIdentity.get(person).knownPersons;
            Set<Node> knownPersonsCopy = new HashSet<>(knownPersons);
            for (Node knownPerson : knownPersonsCopy) {
                knownPersons.addAll(knownPerson.knownPersons);
            }
        }

        Map<Integer, Node> visited = new HashMap<>(personIdentity);

        for (Integer person : personIdentity.keySet()) {
            Node currentPerson = visited.get(person);
            if (currentPerson != null) {
                Set<Node> knownPersons = currentPerson.knownPersons;
                Set<Node> knownPersonsCopy = new HashSet<>(knownPersons);
                for (Node knownPerson : knownPersonsCopy) {
                    if (knownPerson.person != person) {
                        visited.remove(knownPerson.person);
                    }
                }
            }
        }

        return visited.size();

    }

    private static boolean[][] getMatrix(int size, List<String> related) {
        boolean[][] matrix = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            char[] relation = related.get(i).toCharArray();
            matrix[i] = new boolean[size];
            for (int j = 0; j < size; j++) {
                if (relation[j] == '1') {
                    matrix[i][j] = true;
                }
            }
        }
        return matrix;
    }
}


public class Solution {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("110");
        data.add("110");
        data.add("001");
        System.out.println(Result.countGroups(data));
    }


    public static void main2(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("1100");
        data.add("1110");
        data.add("0110");
        data.add("0001");
        System.out.println(Result.countGroups(data));
    }
}
