package com.st.lms.daoInterfaces;

import java.util.List;

import com.st.lms.model.*;

public interface PublisherDAO {
	Publisher save(Publisher Publisher);
	void delete(Publisher Publisher);
	void update(Publisher Publisher);
	Publisher find(int i);
	List<Publisher> findAll();
}
