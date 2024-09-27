package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task_1 {
    public static void main(String[] args){
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
                );

    /*Traditional Approach to Filter Apples*/

    List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
    System.out.println(greenApples);

    List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    System.out.println(redApples);

    /*Filter Apples using predicate*/
    List<Apple> greenApples2 = filter(inventory, new AppleWeightPredicate());
    System.out.println(greenApples2);

    List<Apple> redAndHeavyApple = filter(inventory, new AppleColorAndWeightPredicate());
    System.out.println(redAndHeavyApple);

    List<Apple> redAndHeavyApple1 = filter(inventory, new ApplePredicate() {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equals(Color.RED) && apple.getWeight() >= 120;
        }
    });
    System.out.println(redAndHeavyApple1);
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }


    public interface ApplePredicate{
        boolean test(Apple apple);
    }

    public static class AppleWeightPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple){
            return apple.getWeight() > 120;
        }
    }

    public static class AppleColorAndWeightPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple){
            return apple.getColor().equals(Color.RED) && apple.getWeight() > 150;
        }
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


    public static class Apple{
        int weight;
        Color color;

        public Apple(int weight, Color color){
            this.weight = weight;
            this.color = color;
        }

        public void setWeight(int weight){
            this.weight = weight;
        }

        public void setColor(Color color){
            this.color = color;
        }

        public int getWeight(){
            return weight;
        }

        public Color getColor(){
            return color;
        }

        @Override
        public String toString(){
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    public enum Color{
        RED,
        GREEN,
    }
}
