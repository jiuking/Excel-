package com.cache.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
	Object data;
	volatile boolean cacheValid;
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	List list = new ArrayList<Integer>();
	public void main(){
		list.add(12);
		List<String> list1 = list;
		System.out.println(list.get(0));
		System.out.println(list1.get(0));//jbgm activite snaker Shark osworkflow������ ��� gange 
		//fremaker word תpdf �淶�Ƿ���� ͨ���� �ܽ�õĻ��� �ֲ�ʽ review ��Ʒģ��  mysql���� �Ӻ�������Ч gerrit ������鹤��

		// clone ������ͬʱ����֤�ɶ��ԣ������п��ܴ�������
	}
	
	public static void main(String[] args){
		new Cache().main();
	}
	
	void processCachedData() {
		rwl.readLock().lock();
		if (!cacheValid) {
			// Must release read lock before acquiring write lock
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			// Recheck state because another thread might have acquired
			// write lock and changed state before we did.
			if (!cacheValid) {
				// data = ...
				cacheValid = true;
			}

			// Downgrade by acquiring read lock before releasing write lock
			rwl.readLock().lock();
			rwl.writeLock().unlock(); // Unlock write, still hold read
		}
		// use(data);
		rwl.readLock().unlock();
	}
}
