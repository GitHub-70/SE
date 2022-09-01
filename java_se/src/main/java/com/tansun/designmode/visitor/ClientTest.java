package com.tansun.designmode.visitor;

/**
 *  年底，CEO和CTO开始评定员工一年的工作绩效，员工分为工程师和经理，
 *  CTO关注工程师的代码量、经理的新产品数量；
 *  CEO关注的是工程师的KPI和经理的KPI以及新产品数量。
 *
 * 访问者模式测试：
 *      如果要增加一个访问者，只要新实现一个 Visitor 接口的类，
 *      从而达到数据对象与数据操作相分离的效果。
 *
 *      访问者模式的优点：
 *          1.各角色职责分离，符合单一职责原则。通过UML类图和上面的示例可以看出来，Visitor、
 *            ConcreteVisitor、Element 、ObjectStructure，职责单一，各司其责。
 *          2.具有优秀的扩展性。如果需要增加新的访问者，增加实现类 ConcreteVisitor 就可以快速扩展。
 *          3.使得数据结构和作用于结构上的操作解耦，使得操作集合可以独立变化。
 *            员工属性（数据结构）和CEO、CTO访问者（数据操作）的解耦。
 *          4.灵活性
 *      访问者模式的缺点：
 *          1.具体元素对访问者公布细节，违反了迪米特原则。
 *            CEO、CTO需要调用具体员工的方法。
 *          2.具体元素变更时导致修改成本大。
 *            变更员工属性时，多个访问者都要修改。
 *          3.违反了依赖倒置原则，为了达到“区别对待”而依赖了具体类，没有以来抽象。
 *            访问者 visit 方法中，依赖了具体员工的具体方法
 *
 *
 */
public class ClientTest {

    public static void main(String[] args) {
        // 构建报表
        BusinessReport report = new BusinessReport();
        System.out.println("=========== CEO看报表 ===========");
        report.showReport(new CEOVisitor());
        System.out.println("=========== CTO看报表 ===========");
        report.showReport(new CTOVisitor());


    }
}
