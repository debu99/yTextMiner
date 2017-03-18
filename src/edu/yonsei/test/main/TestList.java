package edu.yonsei.test.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class TestList {  

    private static final int SIZE = 2000000;
    private static final int COUNT = 200;

    public static void main(String[] args) {
        // Set
        Set<Integer> set = new HashSet<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            set.add(i);
        }
        System.out.println("ForArrayWithConvertSetToArray, set, avarage:" + calcAvarage(new ForArrayWithConvertSetToArray(), set));
        System.out.println("ForArrayWithConvertSetToList , set, avarage:" + calcAvarage(new ForArrayWithConvertSetToList(), set));
        System.out.println("ForIterator                  , set, avarage:" + calcAvarage(new ForIterator(), set));
        System.out.println("ForEach                      , set, avarage:" + calcAvarage(new ForEach(), set));
        set = null;

        // List
        List<Integer> list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        System.out.println("ForEach    , list, avarage:" + calcAvarage(new ForEach(), list));
        System.out.println("ForIterator, list, avarage:" + calcAvarage(new ForIterator(), list));
        System.out.println("ForArray   , list, avarage:" + calcAvarage(new ForArray(), list));
        System.out.println("ForArray2  , list, avarage:" + calcAvarage(new ForArray2(), list));
        
    }

    private static long calcAvarage(ForLoop looper, Collection<Integer> collection) {
        long total = 0L;
        for (int i = 0; i < COUNT; i++) {
            total += looper.getForLoopNanoTime(collection);
        }
        return total / COUNT;
    }
    
    public static interface ForLoop {
        long getForLoopNanoTime(Collection<Integer> collection);
    }
    
    public static class ForEach implements ForLoop {
        public long getForLoopNanoTime(Collection<Integer> collection) {
            long start = System.nanoTime();
            long sum = 0;
            for (Integer integer : collection) {
                sum += integer;
            }
            long time = (System.nanoTime()-start);
//            System.out.println("[forEach] class:" + collection.getClass().getSimpleName() + ", time:" + time + ", sum:" +sum);
            return time;
        }
    }

    public static class ForIterator implements ForLoop {
        public long getForLoopNanoTime(Collection<Integer> collection) {
            long start = System.nanoTime();
            long sum = 0;
            for (Iterator<Integer> iterator = collection.iterator(); iterator.hasNext();) {
                ;
                sum += iterator.next();
            }
            long time = (System.nanoTime()-start);
//            System.out.println("[forIterator] class:" + collection.getClass().getSimpleName() + ", time:" + time + ", sum:" +sum);
            return time;
        }
    }

    public static class ForArray implements ForLoop {
        public long getForLoopNanoTime(Collection<Integer> collection) {
            List<Class<?>> interfaces = Arrays.asList(collection.getClass().getInterfaces());
            if (!interfaces.contains(List.class)) {
                throw new IllegalArgumentException();
            }
            
            List<Integer> list = (List<Integer>)collection;
            long start = System.nanoTime();
            long sum = 0;
            for (int i = 1; i < list.size()+1; i++) {
                sum += list.get(i-1);
            }
            long time = (System.nanoTime()-start);
            System.out.println("[forArray] class:" + collection.getClass().getSimpleName() + ", time:" + time + ", sum:" +sum);
            return time;
        }
    }

    public static class ForArray2 implements ForLoop {
        public long getForLoopNanoTime(Collection<Integer> collection) {
            List<Class<?>> interfaces = Arrays.asList(collection.getClass().getInterfaces());
            if (!interfaces.contains(List.class)) {
                throw new IllegalArgumentException();
            }
            
            List<Integer> list = (List<Integer>)collection;
            long start = System.nanoTime();
            long sum = 0;
            for (int i=1, size=list.size()+1; i < size; i++) {
                sum += list.get(i-1);
            }
            long time = (System.nanoTime()-start);
            System.out.println("[forArray2] class:" + collection.getClass().getSimpleName() + ", time:" + time + ", sum:" +sum);
            return time;
        }
    }

    public static class ForArrayWithConvertSetToArray implements ForLoop {
        public long getForLoopNanoTime(Collection<Integer> collection) {
            List<Class<?>> interfaces = Arrays.asList(collection.getClass().getInterfaces());
            if (!interfaces.contains(Set.class)) {
                throw new IllegalArgumentException();
            }
            
            Integer[] array = collection.toArray(new Integer[collection.size()]);
            long start = System.nanoTime();
            long sum = 0;
            for (int i=0 ; i < array.length; i++) {
                sum += array[i];
            }
            long time = (System.nanoTime()-start);
//            System.out.println("[ForArrayWithConvertSetToArray] class:" + collection.getClass().getSimpleName() + ", time:" + time + ", sum:" +sum);
            return time;
        }
    }

    public static class ForArrayWithConvertSetToList implements ForLoop {
        public long getForLoopNanoTime(Collection<Integer> collection) {
            List<Class<?>> interfaces = Arrays.asList(collection.getClass().getInterfaces());
            if (!interfaces.contains(Set.class)) {
                throw new IllegalArgumentException();
            }

            List<Integer> list = new ArrayList<Integer>(collection);
            long start = System.nanoTime();
            long sum = 0;
            for (int i=0 ; i < list.size(); i++) {
                sum += list.get(i);
            }
            long time = (System.nanoTime()-start);
//            System.out.println("[ForArrayWithConvertSetToList] class:" + collection.getClass().getSimpleName() + ", time:" + time + ", sum:" +sum);
            return time;
        }
    }
}  