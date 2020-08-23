/*
 * Copyright (c) 2020, https://github.com/911992 All rights reserved.
 * License BSD 3-Clause (https://opensource.org/licenses/BSD-3-Clause)
 */

 /*
WAsys_pojo_http_data_entity_tool
File: Type_Generator.java
Created on: Jul 26, 2020 5:17:01 PM
    @author https://github.com/911992
 
History:
    0.2.1 (20200823)
        • Changes related to WAsys_simple_generic_object_pool API change version 0.5.1
        • Inline Factory class now implements wasys.lib.java_type_util.reflect.type_sig.Object_Factory
        • Removed redudant wasys.lib.generic_object_pool.api.Poolable_Object import

    initial version: 0.1.0 (20200726)
 */
package wasys.lib.pojo_http_data.entity_tool;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import wasys.lib.pojo_http_data.entity_tool.meta.WAsys_Project_Info;

/**
 *
 * @author https://github.com/911992
 */
public class Type_Generator {

    private static final String ACCELERATED_CACHE_INTERFACE_TYPE = "Fillable_Object_Parse_Cache_Accelerator";

    private static final String ESSENTIAL_IMPORTS_POOLABLE[] = {
        "wasys.lib.java_type_util.reflect.type_sig.Object_Factory",
        "wasys.lib.generic_object_pool.Object_Pool",
        "wasys.lib.generic_object_pool.Pool_Context"
    };
    private static final String ESSENTIAL_IMPORTS_POOLABLE_ALL;

    static {
        StringBuilder _sb = new StringBuilder(275);
        boolean _need_nl = false;
        for (int a = 0; a < ESSENTIAL_IMPORTS_POOLABLE.length; a++) {
            if (_need_nl) {
                _sb.append("\n");
            }
            _sb.append("import ");
            _sb.append(ESSENTIAL_IMPORTS_POOLABLE[a]);
            _sb.append(";");
            _need_nl = true;
        }
        ESSENTIAL_IMPORTS_POOLABLE_ALL = _sb.toString();
    }

    private static final String ESSENTIAL_IMPORTS[] = {
        String.format("wasys.lib.pojo_http_data.api_ex.%s", ACCELERATED_CACHE_INTERFACE_TYPE),
        "wasys.lib.pojo_http_data.api.annotations.No_Param"
    };

    private static final String ESSENTIAL_IMPORTS_ALL;

    static {
        StringBuilder _sb = new StringBuilder(275);
        boolean _need_nl = false;
        for (int a = 0; a < ESSENTIAL_IMPORTS.length; a++) {
            if (_need_nl) {
                _sb.append("\n");
            }
            _sb.append("import ");
            _sb.append(ESSENTIAL_IMPORTS[a]);
            _sb.append(";");
            _need_nl = true;
        }
        ESSENTIAL_IMPORTS_ALL = _sb.toString();
    }

    private static final String BASE_CLASSES_PACKAGE = "wasys.lib.pojo_http_data.api";
    private static final String FILLABLE_POOLABLE_CLASS = "Poolable_Fillable_Object_Adapter";
    private static final String FILLABLE_POOLABLE_CLASS_IMPORT = String.format("%s.%s", BASE_CLASSES_PACKAGE, FILLABLE_POOLABLE_CLASS);

    private static final String FILLABLE_ONLY_CLASS = "Fillable_Object_Adapter";
    private static final String FILLABLE_ONLY_CLASS_IMPORT = String.format("%s.%s", BASE_CLASSES_PACKAGE, FILLABLE_ONLY_CLASS);

    private static final String OBJECT_POOL_POLICY_IMPORT = "wasys.lib.generic_object_pool.Generic_Object_Pool_Policy";

    private static final String POOL_VARIABLE_NAME = "__pool__";

    private static final String INTERNAL_FACTORY_CLASS_NAME = "Factory";

    private static final String NEW_INSTANCE_FUNC_NAME = "get_an_instance";

    private static final String GET_POOL_VAR_FUNC_NAME = "get_pool";

    private static final String BACK_TO_POOL_METHOD_NAME = "back_to_pool";

    private static final String DEFAULT_TYPE_NAME_PLACEHOLDER = "<<my_class(⌐■_■)>>";

    static final String DEFAULT_POL_POLICY_STATEMENT = "Generic_Object_Pool_Policy.DEF_INS";

    static final String TYPE_CACHE_VARIABLE_DEFINITION_VAR_NAME = "__TYPE_SIGNATURE_CACHE__";
    static final String TYPE_CACHE_VARIABLE_DEFINITION = String.format("\t@No_Param private static Object %s;", TYPE_CACHE_VARIABLE_DEFINITION_VAR_NAME);

    private Type_Generator() {
    }

    /**
     * Generates a {@code Fillable_Object} (and as {@code Poolable_Object})
     * entity, from given arguments.
     *
     * @param arg_package package name of the type (could be {@code null}, or
     * zero-length(empty))
     * @param arg_type_name name of the type/class. If {@code null} or
     * zero-length(empty), then a placeholder will be used
     * @param arg_as_poolable state if the generated type should be a
     * {@code Poolable_Object} or not
     * @param arg_pool_policy the string literal/statement to use, as pool
     * policy when initializing the pool var. ({@code null}, or
     * zero-length(empty) string will lead it to default as {@code })
     * @param arg_out the stream result will be written to. If {@code null},
     * then {@code STDOUT} will be used.
     */
    public static final void generate_type(String arg_package, String arg_type_name, boolean arg_as_poolable, String arg_pool_policy, OutputStream arg_out) {

        if (arg_pool_policy == null || arg_pool_policy.length() == 0) {
            arg_pool_policy = DEFAULT_POL_POLICY_STATEMENT;
        }
        if (arg_type_name == null || arg_type_name.length() == 0) {
            arg_type_name = DEFAULT_TYPE_NAME_PLACEHOLDER;
        }
        if (arg_package == null || arg_package.length() == 0) {
            arg_package = null;
        }
        if (arg_out == null) {
            arg_out = System.out;
        }

        PrintStream _out = new PrintStream(arg_out);

        /*printing the package*/
        if (arg_package != null) {
            _out.printf("package %s;\n\n", arg_package);
        }

        /*imports*/
        if (arg_as_poolable) {
            _out.printf("%s\n", ESSENTIAL_IMPORTS_POOLABLE_ALL);
            if (DEFAULT_POL_POLICY_STATEMENT.equals(arg_pool_policy)) {
                _out.printf("import %s;\n", OBJECT_POOL_POLICY_IMPORT);
            }
            _out.printf("import %s;\n", FILLABLE_POOLABLE_CLASS_IMPORT);
        } else {
            _out.printf("import %s;\n", FILLABLE_ONLY_CLASS_IMPORT);
        }
        _out.printf("%s\n\n", ESSENTIAL_IMPORTS_ALL);

        //usage
        _out.printf("/**\n");
        if (arg_as_poolable) {
            _out.printf(" * This class is a fillable, closable and poolable type, make sure any acquired instance will be released once is no more needed.\n");
            _out.printf(" * Once an instance is acquired using the internal pool, simply ask the related wrapper/impl (like Servlet_Request_Data_Handler) to fill it out.\n");
            _out.printf(" * <p>Sample Usage (using {@code try-with-resources}) (<b>recommended</b>)</p>\n");
            _out.printf(" * <pre>\n");
            _out.printf(" * try(%s _ins = %s.%s();){\n", arg_type_name, arg_type_name, NEW_INSTANCE_FUNC_NAME);
            _out.printf(" * //asking to fill the _ins using a wrapper/impl (like Servlet_Request_Data_Handler, as below)\n");
            _out.printf(" * //Servlet_Request_Data_Handler.fill(http_req, _ins);//whete http_req is a HttpServletRequest\n");
            _out.printf(" * //...using _ins\n");
            _out.printf(" * }//automatically releasing the instance\n");
            _out.printf(" * </pre>\n");
            _out.printf(" * \n");
            _out.printf(" * <p>Sample Usage (<b>without</b> {@code try-with-resources})</p>\n");
            _out.printf(" * <pre>\n");
            _out.printf(" * %s _ins = %s.%s();\n", arg_type_name, arg_type_name, NEW_INSTANCE_FUNC_NAME);
            _out.printf(" * ...\n");
            _out.printf(" * _ins.%s();//IMPORTANT\n", BACK_TO_POOL_METHOD_NAME);
            _out.printf(" * </pre>\n");
        } else {
            _out.printf(" * This class is a fillable type, but not a closable(by default), so cannot be used using a try-with-resources.\n");
            _out.printf(" * Once an instance is created, simply ask the related wrapper/impl (like Servlet_Request_Data_Handler) to fill it out.\n");
            _out.printf(" * <p>Sample Usage</p>\n");
            _out.printf(" * <pre>\n");
            _out.printf(" * %s _ins = new %s();\n", arg_type_name, arg_type_name);
            _out.printf(" * //asking to fill the _ins using a wrapper/impl (like Servlet_Request_Data_Handler, as below)\n");
            _out.printf(" * //Servlet_Request_Data_Handler.fill(http_req, _ins);//whete http_req is a HttpServletRequest\n");
            _out.printf(" * //...using _ins\n");
            _out.printf(" * </pre>\n");
        }
        _out.printf(" * This class is final because of implemented %s\n", ACCELERATED_CACHE_INTERFACE_TYPE);
        _out.printf(" */\n");

        /*class definition*/
        
        _out.printf("final public class %s extends ", arg_type_name);
        if (arg_as_poolable) {
            _out.print(FILLABLE_POOLABLE_CLASS);
        } else {
            _out.print(FILLABLE_ONLY_CLASS);
        }
        /*accelerated interface*/
        _out.printf(" implements %s{\n\n", ACCELERATED_CACHE_INTERFACE_TYPE);


        /*printing the tool siganture*/
        _out.printf("\t/*\n");
        _out.printf("\t  This entity was generated by\n");
        _out.printf("\t  %s\n", WAsys_Project_Info.PROJECT_SIGNATURE);
        _out.printf("\t  %s\n", WAsys_Project_Info.PROJECT_REPO);
        _out.printf("\t-----------\n");
        _out.printf("\t  File was created on:%s\n", new Date(System.currentTimeMillis()));
        _out.printf("\t*/\n\n");

        /*commend to user-defined stuffs*/
        _out.printf("\t/*User-defined param definitions --- start*/\n\n");

        _out.printf("\t/*User-defined param definitions ----- end*/\n\n");

        /*reset func*/
        _out.print("\t@Override\n");
        _out.printf("\tprotected void child_reset_state() {\n");
        _out.print("\t\t/*TODO, reset the object state*/\n");
        _out.printf("\t}\n\n");

        if (arg_as_poolable) {
            /*pool initialization*/
            _out.printf("\t/*object pool*/\n");
            _out.printf("\t@No_Param final static Object_Pool %s;\n", POOL_VARIABLE_NAME);
            _out.printf("\t/*object pool initialization*/\n");
            _out.printf("\tstatic{\n");
            _out.printf("\t\t%s = Pool_Context.get_insatcne().get_pool_unregistered_synced(new %s(),%s);\n", POOL_VARIABLE_NAME, INTERNAL_FACTORY_CLASS_NAME, arg_pool_policy);
            _out.printf("\t}\n\n");

//            /*working variable*/
//            _out.printf("\t/*Working variable\n\t  •true when object is being used\n\t  •false when idle and in pool context\n\t  please leave it alone*/\n");
//            _out.printf("\t@No_Param private volatile boolean %s;\n\n", WORKING_VARIALBE_NAME);

            /*factory class*/
            _out.printf("\t/*internal factory class for the type*/\n");
            _out.printf("\tprivate static class %s implements Object_Factory<%s>{\n\n", INTERNAL_FACTORY_CLASS_NAME,arg_type_name);
            _out.printf("\t\t@Override\n");
            _out.printf("\t\tpublic %s create_object(Class arg_type) {\n",arg_type_name);
            _out.printf("\t\t\treturn new %s();\n", arg_type_name);
            _out.printf("\t\t}\n\n");
            _out.printf("\t}\n\n");

            /*static method for getting a new instnace*/
            _out.printf("\t/*Returns a pooled instance from the pool*/\n");
            _out.printf("\tpublic static final %s %s(){\n", arg_type_name, NEW_INSTANCE_FUNC_NAME);
            _out.printf("\t\t%s _res = (%s)%s.get_an_instance();\n", arg_type_name, arg_type_name, POOL_VARIABLE_NAME);
//            _out.printf("\t\t_res.%s=true;\n", WORKING_VARIALBE_NAME);
            _out.printf("\t\treturn _res;\n");
            _out.printf("\t}\n\n");

            /*private constructor*/
            _out.printf("\tprivate %s(){\n\t\t/*User-defined instance initialization(if any)*/\n\t}\n\n", arg_type_name);

//            /*getter for valdiation*/
//            _out.printf("\t/*(internal use)method to check if instance is valid or not*/\n");
//            _out.printf("\tprivate boolean %s(){\n", WORKING_VARIALBE_GETTER_FUNC_NAME);
//            _out.printf("\t\treturn (%s==false);\n\t}\n\n", WORKING_VARIALBE_NAME);
//
//            /*setter for valdiation*/
//            _out.printf("\t/*(internal use)invalidates this instance*/\n");
//            _out.printf("\tprivate void %s(){\n", WORKING_VARIALBE_SETTER_FUNC_NAME);
//            _out.printf("\t\t%s=false;\n\t}\n\n", WORKING_VARIALBE_NAME);

        } else {
            /*public constructor*/
            _out.printf("\tpublic %s(){\n\t\t/*User-defined instance initialization(if any)*/\n\t}\n\n", arg_type_name);
        }

        

        /*accelerated interface methods*/
        _out.printf("\t/*Type-level global cache ,DO NO CHANGE/TOUCH*/\n");
        _out.printf(TYPE_CACHE_VARIABLE_DEFINITION);
        _out.printf("\n\n");

        _out.printf("\t/*Setter for type-level global cache ,DO NO CHANGE/TOUCH*/\n");
        _out.printf("\t@Override public void _api_ex_set_type_parse_result(Object arg_arg) { %s = arg_arg; }\n\n", TYPE_CACHE_VARIABLE_DEFINITION_VAR_NAME);

        _out.printf("\t/*Getter for type-level global cache ,DO NO CHANGE/TOUCH*/\n");
        _out.printf("\t@Override public Object _api_ex_get_type_parse_result() {return %s;}\n\n", TYPE_CACHE_VARIABLE_DEFINITION_VAR_NAME);

        /*end of class def*/
        _out.printf("}\n");

        _out.flush();

    }
}
