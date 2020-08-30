/*
 * Copyright (c) 2020, https://github.com/911992 All rights reserved.
 * License BSD 3-Clause (https://opensource.org/licenses/BSD-3-Clause)
 */

/*
WAsys_pojo_http_data_entity_tool
File: WAsys_Project_Info.java
Created on: Jul 26, 2020 4:46:01 PM
    @author https://github.com/911992
 
History:
    0.2.7 (20200829)
        • Updated PROJECT_VERSION, and PROJECT_BUILD fields

    0.2.1 (20200823)
        • Updated PROJECT_VERSION, and PROJECT_BUILD fields

    initial version: 0.1.0 (20200726)
*/

package wasys.lib.pojo_http_data.entity_tool.meta;


/**
 * 
 * @author https://github.com/911992
 */
public class WAsys_Project_Info {
    public static final String PROJECT_REPO="https://github.com/911992/WAsys_pojo_http_data_entity_tool";
    public static final String PROJECT_VERSION="0.2.7";
    public static final String PROJECT_BUILD="20200829";
    public static final String PROJECT_NAME="WAsys Fillable_Object Skeleton Generator";
    public static final String PROJECT_DESC="Simple tool for making skeleton of Fillable_Object(and as Poolable_Object) (WAsys_pojo_http_data) entities";
    private WAsys_Project_Info(){}
    public static final String PROJECT_SIGNATURE = String.format("%s v%s(%s)", PROJECT_NAME,PROJECT_VERSION,PROJECT_BUILD);
}
