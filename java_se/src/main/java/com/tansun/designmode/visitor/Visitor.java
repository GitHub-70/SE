package com.tansun.designmode.visitor;

/**
 * 不同层级的
 *      访问的内容不同，即访问方法就不同
 */
public interface Visitor {

    // 访问工程师类型
    void visit(Engineer engineer);

    // 访问经理类型
    void visit(Manager manager);
}
