package com.st.lms.daoInterfaces;

import java.util.List;

import com.st.lms.model.*;

public interface AuthorDAO {
	Author save(Author author);
	void delete(Author author);
	void update(Author author);
	Author find(int i);
	List<Author> findAll();
}
