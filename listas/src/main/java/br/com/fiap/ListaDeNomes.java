package br.com.fiap;

import java.util.*;

public class ListaDeNomes {

    public static void main(String[] args) {
        //hashSet();
        //sortedSet();
        linkedList();
    }

    static void hashSet()
    {
        Set<String> nomes = new HashSet<String>();
        nomes.add("Aluno 1");
        nomes.add("Aluno 2");
        nomes.add("Aluno 3");
        System.out.println(nomes);
        System.out.println(nomes.contains("Aluno 1"));
    }

    static void sortedSet()
    {
        SortedSet<String> nomes = new TreeSet<String>();

        nomes.add("Aluno 1");
        nomes.add("Aluno 2");
        nomes.add("Aluno 3");

        System.out.println(nomes);
        System.out.println(nomes.contains("Aluno 2"));
    }

    static void linkedList()
    {
        LinkedList<String> nomes = new LinkedList<>();

        nomes.add("Aluno 5");
        nomes.add("Aluno 3");
        nomes.add("Aluno 1");

        System.out.println(nomes);
        System.out.println(nomes.contains("Aluno 2"));
    }

}
