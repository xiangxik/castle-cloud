package me.xiangxik.data.domain;

public interface Stateful<T> {

	T getState();

	void setState(T state);

}
