package com.stock;

import java.text.DecimalFormat;

/**
 * @author Chen Xiao
 * 网格交易
 * @since 2021-07-14 15:18
 */
public class Grids {

    static DecimalFormat df = new DecimalFormat("##0.000");

    static double ZS_PRICE = 51.164;
    static double BJ_PRICE = 1.379;

    /**
     * @param args
     */
    public static void main(String[] args) {
        GridsZS();
        GridsBJEtf();
    }

    /**
     * 食品基金网格 512690
     */
    private static void GridsSPEtf() {

    }

    /**
     * 白酒基金网格 512690
     */
    private static void GridsBJEtf() {
        System.out.println("//*************白酒start****************//");
        //1.379 * 64800 = 89359
        //每涨跌1%  ±5000大洋
        //10次每次5k就是5W 剩下3W当做底仓
        double price = 1.377;
        int sell = 5000;
        int totalStock = 59800;
        getPrice(price, 0.02, 5, sell, totalStock);
        System.out.println("============建仓" + price + ",±2%买入卖出==============");
        getPrice(price, -0.02, 5, sell, totalStock);
        System.out.println("//**************白酒end***************//");
    }

    /**
     * 招商网格 等比数列
     */
    private static void GridsZS() {
        System.out.println("//*************招商start****************//");
        //51.164 * 3000股 = 153492
        //每涨跌2%  ±100股
        int totalStock = 3000;
        double price = ZS_PRICE;
        int sellNum = 100;
        getStockNum(price, 0.015, 10, sellNum, totalStock);
        System.out.println("============建仓：" + price + ",±1.5%%买入卖出==============");
        getStockNum(price, -0.015, 10, sellNum, totalStock);
        System.out.println("//**************招商end***************//");
    }

    /**
     * 卖出价格
     *
     * @param price
     * @param up
     * @param count 交易次数
     */
    private static void getPrice(double price, double up, int count, int sellPrice, int totalStock) {
        double profit = -(BJ_PRICE - price * (1 + up)) * totalStock;
        double v = price * (1 + up);
        int stock = new Double(sellPrice / v).intValue();
        if (up < 0.0) {
            System.out.println(df.format(v) + "买入：" + sellPrice + "元," + "亏：" + df.format(profit));
            if (count > 0) {
                getPrice(v, up, --count, sellPrice, totalStock + stock);
            }
        }
        if (up > 0.0) {
            if (count > 0) {
                getPrice(v, up, --count, sellPrice, totalStock - stock);
            }
            System.out.println(df.format(v) + "卖出：" + sellPrice + "元," + "赚：" + df.format(profit));
        }
    }

    /**
     * 卖出股数
     *
     * @param price
     * @param up
     * @param count
     */
    private static void getStockNum(double price, double up, int count, int sell, int totalStock) {
        double profit = -(ZS_PRICE - price * (1 + up)) * totalStock;
        double v = price * (1 + up);
        if (up < 0.0) {
            System.out.println(df.format(v) + "买入：" + sell + "股," + "亏:" + df.format(profit));
            if (count > 0) {
                getStockNum(v, up, --count, sell, totalStock + sell);
            }
        }
        if (up > 0.0) {
            if (count > 0) {
                getStockNum(v, up, --count, sell, totalStock - sell);
            }
            System.out.println(df.format(v) + "卖出：" + sell + "股," + "赚" + df.format(profit));
        }
    }

}
