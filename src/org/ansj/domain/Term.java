package org.ansj.domain;

import org.ansj.util.MathUtil;

public class Term implements Comparable<Term> {
	public static final Term NULL = new Term("NULL", 0, TermNatures.NULL);
	// 当前词
	private String name;
	// 当前词的起始位置
	private int offe;
	// 词性列表
	private Path[] paths = null;
	// 词性列表
	private TermNatures termNatures = null;
	// 最大路径
	// private Path maxPath = Path.NULLPATH;
	// 同一行内数据
	private Term next;
	// 分数
	public double score = 0;
	// 本身分数
	public double selfScore = 1;

	private Term from;
	private Term to;

	public Term(String name, int offe, TermNatures termNatures) {
		super();
		this.name = name;
		this.offe = offe;
		this.termNatures = termNatures;
	}

	// 可以到达的位置
	public int getToValue() {
		return offe + name.length();
	}

	public int getOffe() {
		return offe;
	}

	public void setOffe(int offe) {
		this.offe = offe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 核心构建最优的路径
	 * 
	 * @param term
	 */
	public void setPathScore(Term from) {
		// 维特比进行最优路径的构建
		double score = MathUtil.compuScore(from, this);
		if (this.from == null || this.getScore() >= score) {
			this.setFromAndScore(from, score);
		}
	}

	/**
	 * 核心分数的最优的路径
	 * 
	 * @param term
	 */
	public void setPathPersonScore(Term from) {
		double score = this.selfScore + from.getScore();
		// 维特比进行最优路径的构建
		if (this.from == null || this.getScore() < score) {
//System.out.println(from+"@"+this+"\t"+score);
			this.setFromAndScore(from, score);
		}
	}

	/**
	 * 根据词频词长最优的路径
	 * 
	 * @param term
	 */
	public void setPathScoreByFreq(Term from) {
		// 维特比进行最优路径的构建
		double score = MathUtil.compuScoreFreq(from, this);
		if (this.from == null || this.getScore() <= score) {
			this.setFromAndScore(from, score);
		}
	}

	private void setFromAndScore(Term from, double score) {
		// TODO Auto-generated method stub
		this.from = from;
		this.score = score;
	}

	public String toString() {
		return this.name + "/";
	}

	/**
	 * 进行term合并
	 * 
	 * @param term
	 * @param maxNature
	 */
	public Term merage(Term to) {
		this.name = this.name + to.getName();
		return this;
	}

	/**
	 * 更新偏移量
	 * 
	 * @param offe
	 */
	public void updateOffe(int offe) {
		// TODO Auto-generated method stub
		this.offe += offe;
	}

	public Term getNext() {
		return next;
	}

	/**
	 * 返回他自己
	 * 
	 * @param next
	 *            设置他的下一个
	 * @return
	 */
	public Term setNext(Term next) {
		this.next = next;
		return this;
	}

	public double getScore() {
		// TODO Auto-generated method stub
		return this.score;
	}

	public Term getFrom() {
		return from;
	}

	public Term getTo() {
		return to;
	}

	public void setFrom(Term from) {
		this.from = from;
	}

	public void setTo(Term to) {
		this.to = to;
	}

	/**
	 * 获得这个term的所有词性
	 * 
	 * @return
	 */
	public TermNatures getTermNatures() {
		return termNatures;
	}

	@Override
	public int compareTo(Term o) {
		// TODO Auto-generated method stub
		if (this.score > o.score) {
			return 0;
		} else {
			return 1;
		}
	}

}
