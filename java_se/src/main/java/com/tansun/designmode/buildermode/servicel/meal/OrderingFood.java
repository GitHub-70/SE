package com.tansun.designmode.buildermode.servicel.meal;

import com.tansun.designmode.buildermode.servicel.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName OrderingFood
 * @Description 点餐
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/21 16:36
 * @Copyright © 2020 阿里巴巴
 *
 * 建造者模式
 */
public class OrderingFood {

    private List<Item> items = new ArrayList<Item>();

    /* *
     * @Author 吴槐
     * @Description 点餐
     * @Date 16:46 2022/9/21
     * @Param item
     * @return void
     *       
     **/
    public void addItem(Item item,Integer num){
        // 此判断需要重写 item 的 equals 方法
        if (items.contains(item)){
            String itemClass = item.getClass().getName();
            String itmeName = itemClass.substring(itemClass.lastIndexOf(".") + 1);
            System.out.println(itmeName + "已添加，请勿重复添加！可以改变商品"+itmeName+"的数量："+num);
            List<Item> itemList = items.stream().filter(item1 -> item1.equals(item)).collect(Collectors.toList());
            // 为之前已添加商品 增加数量
            itemList.forEach(item1 -> item1.setItemNumber(item1.getItemNumber() + num));
            return;
        }
        items.add(item);
        item.setItemNumber(num);
    }

    /* *
     * @Author 吴槐
     * @Description 计算总价格
     * @Date 16:41 2022/9/21
     * @Param 
     * @return float
     *       
     **/
    public BigDecimal getCost(){
        BigDecimal cost = new BigDecimal(0);
        for (Item item : items) {
            cost = item.price().multiply(new BigDecimal(item.getItemNumber()))
                    .add(cost);
        }
        return cost;
    }

    /* *
     * @Author 吴槐
     * @Description 展示所订套餐 
     * @Date 16:42 2022/9/21
     * @Param 
     * @return void
     *       
     **/
    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", number : "+item.getItemNumber());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}
