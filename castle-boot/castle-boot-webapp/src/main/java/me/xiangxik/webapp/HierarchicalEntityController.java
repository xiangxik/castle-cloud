package me.xiangxik.webapp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Sets;
import com.querydsl.core.types.Predicate;

import me.xiangxik.data.domain.Node;
import me.xiangxik.data.domain.Result;
import me.xiangxik.data.domain.Tree;
import me.xiangxik.data.domain.TreeHelper;
import me.xiangxik.data.jpa.HierarchicalEntity;
import me.xiangxik.data.jpa.HierarchicalEntityService;

public abstract class HierarchicalEntityController<T extends HierarchicalEntity<?, I, T>, I extends Serializable>
		extends EntityController<T, I> {

	@Autowired
	private HierarchicalEntityService<T, I> service;

	@Override
	protected void onShowListPage(Model model) {
		Tree<T> tree = service.findTree(null);

		model.addAttribute("entities", TreeHelper.toList(tree));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onShowEditPage(T entity, Model model) {
		if (entity.isNew()) {
			entity.setParent((T) getParameter("parent", entity.getClass()));
		}
	}

	@RequestMapping(value = "/addChild", method = RequestMethod.POST)
	@ResponseBody
	public Node<T> doAddChild(@RequestParam(value = "parent", required = false) T parent) {
		T entity = service.initDomain();
		entity.setParent(parent);

		onAddChild(entity);

		Node<T> node = TreeHelper.toNode(service.save(entity), null);
		node.setText(getNewChildName(entity));
		return node;
	}

	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public Tree<T> doTree(@RequestParam(value = "checked", required = false) T[] checked, Predicate predicate) {
		Tree<T> tree = service.findTree(predicate, null).setTextProperty("name");
		tree.makeExpandAll();

		if (checked != null && checked.length > 0) {
			tree.setChecked(Sets.newHashSet(checked));
			tree.makeCheckable();
		}
		return tree;
	}

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	@ResponseBody
	public Result doSort(@RequestParam(value = "source") T source, @RequestParam(value = "target") T target,
			String action) {
		return Result.success().addProperties("data", service.sort(source, target, action));
	}

	protected void onAddChild(T entity) {

	}

	protected void onTree(Node<T> rootNode, Predicate predicate) {

	}

	protected String getNewChildName(T entity) {
		return "子节点";
	}

	protected String getRootName(T root) {
		return "全部";
	}

}
