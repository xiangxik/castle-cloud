package me.xiangxik.data.jpa;

import java.io.Serializable;
import java.util.List;

import com.querydsl.core.types.Predicate;

import me.xiangxik.data.domain.Node;
import me.xiangxik.data.domain.Tree;

public class HierarchicalEntityService<T extends HierarchicalEntity<?, I, T>, I extends Serializable>
		extends EntityService<T, I> {

	@Override
	public HierarchicalEntityRepository<T, I> getRepository() {
		return (HierarchicalEntityRepository<T, I>) super.getRepository();
	}

	public List<T> findRoots() {
		return getRepository().findRoots();
	}

	public List<T> findAllChildren(T root) {
		return getRepository().findAllChildren(root);
	}

	public Tree<T> findByRoot(T root) {
		return getRepository().findByRoot(root);
	}

	public Tree<T> findTree(Predicate predicate) {
		return getRepository().findTree(predicate);
	}

	public Tree<T> findTree(Predicate predicate, Node<T> singleRoot) {
		return getRepository().findTree(predicate, singleRoot);
	}

	public T sort(T source, T target, String action) {
		return getRepository().sort(source, target, action);
	}

}
