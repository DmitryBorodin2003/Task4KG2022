package ru.vsu.cs.kg2022.borodin_d_n.task4.draw;

public interface IFilter<T> {
    boolean accept(T val);
}
