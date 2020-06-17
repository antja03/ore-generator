package me.antja03.oregenerator.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Anthony A.
 * 3/20/2020
 * 3:12 PM
 **/
public class WeightedList<T>
{
    private T defaultItem;
    private List<Entry> entries;
    private double accumulatedWeight;
    private Random random;

    public WeightedList(T defaultItem)
    {
        this.defaultItem = defaultItem;
        this.entries = new ArrayList<>();
        this.accumulatedWeight = 0;
        this.random = new Random();
    }

    public void add(T item, double weight)
    {
        accumulatedWeight += weight;
        entries.add(new Entry(item, accumulatedWeight));
    }

    public void remove(T item)
    {
        entries.removeIf(entry -> entry.item == item);
    }

    public T getRandom()
    {
        double randomWeight = random.nextDouble() * accumulatedWeight;

        for (Entry entry : entries)
            if (entry.weight >= randomWeight) return entry.item;

        return defaultItem;
    }

    private class Entry
    {
        public T item;
        public double weight;

        public Entry(T item, double weight)
        {
            this.item = item;
            this.weight = weight;
        }
    }

}
