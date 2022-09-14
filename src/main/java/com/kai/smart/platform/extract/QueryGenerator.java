package com.kai.smart.platform.extract;


import com.kai.smart.platform.model.Condition;
import com.kai.smart.platform.model.ExtractRequest;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.util.List;


@Service
public class QueryGenerator {
    /**
     * 최종 쿼리 리턴
     * @param extractRequest
     * @return
     */
    public String genFullQuery(ExtractRequest extractRequest){
        String WHERE_DUMMY_TEMPLATE=" WHERE 1=1 ";
        String fullQuery=
                getSelectCause(extractRequest.getSelectColumn()) +
                        getFromCause(extractRequest.getFromTable()) +
                        WHERE_DUMMY_TEMPLATE +
                        getWhereFromCause(extractRequest.getWhereFrom()) +
                        getWhereToCause(extractRequest.getWhereTo());
                if(extractRequest.getWhereIn()!=null) {
                    fullQuery=fullQuery + getWhereInCause(extractRequest.getWhereIn());
                }
                if(extractRequest.getWhereEqual() != null) {
                    fullQuery=fullQuery + getWhereEqualCause(extractRequest.getWhereEqual());
                }

        return fullQuery;
    }



    /**
     * select 절 생성
     * @param selectColumns
     * @return
     */
    private String getSelectCause(List<String> selectColumns){
        ST SELECT_TEMPLATE = new ST("SELECT <columns>");
        SELECT_TEMPLATE.add("columns",String.join(",",selectColumns));
        return SELECT_TEMPLATE.render();
    }

    /**
     * from 절 생성
     * @param fromTable
     * @return
     */
    private String getFromCause(String fromTable){
        ST FROM_TEMPLATE= new ST(" FROM <table> ");
        FROM_TEMPLATE.add("table",fromTable);
        return FROM_TEMPLATE.render();
    }

    /**
     * where from 절 생성
     * @param whereFrom
     * @return
     */
    private String getWhereFromCause(Condition whereFrom){
        ST WHERE_FROM_TEMPLATE= new ST(" AND <whereFromColumn> >= <whereFromValue> ");
         if(whereFrom.getType().equalsIgnoreCase("STRING")) {
             WHERE_FROM_TEMPLATE.add("whereFromColumn",whereFrom.getColumn());
             WHERE_FROM_TEMPLATE.add("whereFromValue","'"+whereFrom.getValue()+"'");
         }else{
             WHERE_FROM_TEMPLATE.add("whereFromColumn",whereFrom.getColumn());
             WHERE_FROM_TEMPLATE.add("whereFromValue",whereFrom.getValue());
         }
        return WHERE_FROM_TEMPLATE.render();
    }

    /**
     * where to 절 생성
     * @param whereTo
     * @return
     */
    private String getWhereToCause(Condition whereTo){
        ST WHERE_TO_TEMPLATE= new ST(" AND <whereToColumn> >= <whereToValue> ");
        if(whereTo.getType().equalsIgnoreCase("STRING")) {
            WHERE_TO_TEMPLATE.add("whereToColumn",whereTo.getColumn());
            WHERE_TO_TEMPLATE.add("whereToValue","'"+whereTo.getValue()+"'");
        }else{
            WHERE_TO_TEMPLATE.add("whereToColumn",whereTo.getColumn());
            WHERE_TO_TEMPLATE.add("whereToValue",whereTo.getValue());
        }
        return WHERE_TO_TEMPLATE.render();
    }


    /**
     * where in 절 생성
     * @param whereIn
     * @return
     */
    private String getWhereInCause(List<Condition> whereIn){
        ST WHERE_IN_TEMPLATE= new ST(" AND <whereInColumn> IN( <whereInValue> )");
        String values="";
        if(whereIn.size()==0){
            return "";
        }else{
            values=generateWhereInStr(whereIn);
            WHERE_IN_TEMPLATE.add("whereInColumn",whereIn.get(0).getColumn());
            WHERE_IN_TEMPLATE.add("whereInValue",values);
        }
        return WHERE_IN_TEMPLATE.render();
    }

    /**
     * where in 절 괄호 안의 문자열 생성
     * @param whereIn
     * @return
     */
    private String generateWhereInStr(List<Condition> whereIn) {
        String values="";
        if (whereIn.get(0).getType().equalsIgnoreCase("STRING")) {
            for (int j = 0; j < whereIn.size(); j++) {
                Object value = whereIn.get(j).getValue();
                if (j != whereIn.size() - 1) values = values + "'" + value + "',";
                else values = values + "'" + value + "'";
            }
        } else {
            values.replaceAll("'", "");
            for (int j = 0; j < whereIn.size(); j++) {
                Object value = whereIn.get(j).getValue();
                if (j != whereIn.size() - 1) values = values + value + ",";
                else values = values + value;
            }
        }
        if(whereIn.size()==1) values = values.replace(",", "");
        return values;
    }


    /**
     * where equal 절 생성
     * @param whereEqual
     * @return
     */
    private String getWhereEqualCause(List<Condition> whereEqual){
        ST WHERE_EQUAL_TEMPLATE= new ST(" AND  <whereEqualColumn> = <whereEqualValue> ");
        for (int i = 0; i < whereEqual.size(); i++) {
            if (whereEqual.get(i).getType().equalsIgnoreCase("STRING")) {
                WHERE_EQUAL_TEMPLATE.add("whereEqualColumn",whereEqual.get(0).getColumn());
                WHERE_EQUAL_TEMPLATE.add("whereEqualValue","'"+whereEqual.get(i).getValue()+"'");
            }else{
                WHERE_EQUAL_TEMPLATE.add("whereEqualColumn",whereEqual.get(0).getColumn());
                WHERE_EQUAL_TEMPLATE.add("whereEqualValue",whereEqual.get(i).getValue());
            }
        }
        return WHERE_EQUAL_TEMPLATE.render();
    }
}
