package me.xiangxik.data.domain;

/**
 * 可默认的，如为true，则为默认对象
 * 
 * @作者 kongxiangxi
 * @博客 http://ken.whenling.com
 * @创建时间 2017年4月23日 下午10:06:17
 */
public interface Defaultable {
	
	public boolean isDefaulted();

	public void setDefaulted(boolean defaulted);

	/**
	 * 标识为默认
	 */
	public void markDefaulted();

}
