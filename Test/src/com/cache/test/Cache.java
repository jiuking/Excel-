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
		System.out.println(list1.get(0));//jbgm activite snaker Shark osworkflow工作流 框架 gange 
		//fremaker word 转pdf 规范是否合适 通用性 总结好的坏的 分布式 review 产品模型  mysql索引 加函数则无效 gerrit 代码审查工具

		// clone 对象相同时，保证可读性，代码有可能错误发生率
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
