package org.test.sales.factory;

import org.test.sales.repo.SalesRepo;
import org.test.sales.repo.SalesRepoImpl;

public class RepoFactory {

	private static SalesRepo repo = new SalesRepoImpl();

	public static SalesRepo getSalesRepo() {
		return repo;
	}

}
