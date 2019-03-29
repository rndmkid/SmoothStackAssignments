package com.st.lms.daoInterfaces;

import java.util.List;

import com.st.lms.model.*;

public interface BookDAO {
	void save(Book Book);
	void delete(Book Book);
	void update(Book Book);
	Book find(int i);
	List<Book> findAll();
}
