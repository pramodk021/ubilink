package com.ubilink.gservice;

import java.util.List;

public interface GenericService<T> {
	public void delete(int id);

	public T saveAndFlush(T entity);

	public List<T> findAll();

	public T findOne(int id);

	long count();
}
