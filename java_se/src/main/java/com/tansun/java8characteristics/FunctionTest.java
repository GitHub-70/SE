package com.tansun.java8characteristics;

import com.tansun.basic.pojo.SysRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * SysRole
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<SysRole, String> fnc = a -> a.getName();
//        String apply = fnc.apply(5);
    }

    private static void  applyTest(){
        Function<SysRole, String> funcEmpToString= (SysRole e)-> {return e.getName();};
        List<SysRole> SysRoleList=
                Arrays.asList(new SysRole("Tom Jones", 45),
                        new SysRole("Harry Major", 25),
                        new SysRole("Ethan Hardy", 65),
                        new SysRole("Nancy Smith", 15),
                        new SysRole("Deborah Sprightly", 29));
        List<String> empNameList=convertEmpListToNamesList(SysRoleList, funcEmpToString);
        empNameList.forEach(System.out::println);
    }


    public static List<String> convertEmpListToNamesList(List<SysRole> SysRoleList, Function<SysRole, String> funcEmpToString){
        List<String> empNameList=new ArrayList<String>();
        for(SysRole emp:SysRoleList){
            empNameList.add(funcEmpToString.apply(emp));
        }
        return empNameList;
    }
}
