/*
 * Copyright (c) 2020, https://github.com/911992 All rights reserved.
 * License BSD 3-Clause (https://opensource.org/licenses/BSD-3-Clause)
 */

/*
WAsys_pojo_http_data_entity_tool
File: Main_CLI.java
Created on: Jul 26, 2020 4:51:23 PM
    @author https://github.com/911992
 
History:
    initial version: 0.1.0 (20200726)
*/

package wasys.lib.pojo_http_data.entity_tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import wasys.lib.pojo_http_data.entity_tool.meta.WAsys_Project_Info;


/**
 * 
 * @author https://github.com/911992
 */
public class Main_CLI {

    private static String working_args[];

    private static HashMap<String, String> PARSED_DATA = new HashMap<String, String>();

    private static final String CMD_GUI_MODE = "-fancy";
    private static final String CMD_SHOW_HELP = "-h";
    private static final String CMD_TYPE_NAME = "-class";
    private static final String CMD_PACKAGE_NAME = "-package";
    private static final String CMD_ALSO_POOLABLE = "-poolable";
    private static final String CMD_POLICY_STATEMENT = "-pol";
    private static final String CMD_FILE_OUT = "-fout";
    private static final String CMD_CLASS_NAME_FILE_OUT = "-save";
    
    private static final PrintStream out=System.out;

    private static boolean parse_args() {
        String _cmd;
        String _data;
        int _cmd_len = working_args.length;
        boolean _read_data = false;
        for (int a = 0; a < _cmd_len; a++) {
            _cmd = working_args[a];
            switch (_cmd) {
                case CMD_TYPE_NAME:
                case CMD_PACKAGE_NAME:
                case CMD_POLICY_STATEMENT:
                case CMD_FILE_OUT: {
                    _read_data = true;
                    break;
                }
                case CMD_CLASS_NAME_FILE_OUT:
                case CMD_GUI_MODE:
                case CMD_ALSO_POOLABLE:
                case CMD_SHOW_HELP: {
                    _read_data = false;
                    break;
                }
                default:{
                    continue;
                }
            }
            if (_read_data) {
                if ((a + 1) < _cmd_len) {
                    a = a + 1;
                    _data = working_args[a];
                }else{
                    System.err.printf("Error, expected value for %s\n",_cmd);
                    _data = null;
                    return false;
                }
                _read_data = false;
            }else{
                _data= null;
            }
            PARSED_DATA.put(_cmd, _data);
        }
        return true;
    }

    private static boolean shall_show_help() {
        if(PARSED_DATA.isEmpty()){
            System.err.printf("Error! zero-length input argument is not expected!\n");
            System.err.flush();
            return true;
        }
        return PARSED_DATA.containsKey(CMD_SHOW_HELP);
    }
    
    private static void show_help(){
        out.printf("%s\n",WAsys_Project_Info.PROJECT_SIGNATURE);
        out.printf("%s\n", WAsys_Project_Info.PROJECT_REPO);
        out.printf("-----------\n");
        out.printf("Command Lines:\n");
        out.printf("%s\t\t\t\t\t:Shows this help(ignore other cmds)\n",CMD_SHOW_HELP);
        out.printf("%s\t\t\t\t\t:Runs the tool in GUI mode\n",CMD_GUI_MODE);
        out.printf("%s <<entity_type_name>>\t\t:Tells the type's name(could be skipped)\n",CMD_TYPE_NAME);
        out.printf("%s <<entity_package_name>>\t:Tells the type's package name(could be skipped)\n",CMD_PACKAGE_NAME);
        out.printf("%s\t\t\t\t:Tells to make the object as a Poolable_Object too\n", CMD_ALSO_POOLABLE);
        out.printf("%s <<pool_policy_stmt>>\t\t:Tells the statement/syntax for setting the pool policy variable(could be skipped, will be skipped if %s is absent)\n",CMD_POLICY_STATEMENT,CMD_ALSO_POOLABLE);
        out.printf("%s\t\t\t\t\t:Saves the output to a .java file to current path(required %s, ignores %s)\n",CMD_CLASS_NAME_FILE_OUT,CMD_TYPE_NAME,CMD_FILE_OUT);
        out.printf("%s <<file_out>>\t\t\t:Specifies the file as output(if absent, STDOUT will be used)\n",CMD_FILE_OUT);
    }
    
    private static boolean show_in_gui_mode(){
        return PARSED_DATA.containsKey(CMD_GUI_MODE);
    }
    
    private static OutputStream get_result_out(){
        String _file_path=null;
        if(PARSED_DATA.containsKey(CMD_CLASS_NAME_FILE_OUT)){
            _file_path = PARSED_DATA.get(CMD_TYPE_NAME);
            if(_file_path==null){
                System.err.printf("Missed required %s <<entity_type_name>> when using %s\n",CMD_TYPE_NAME,CMD_CLASS_NAME_FILE_OUT);
                return null;
            }
            _file_path = String.format("%s.java", _file_path);
        }else if (PARSED_DATA.containsKey(CMD_FILE_OUT)){
            _file_path = PARSED_DATA.get(CMD_FILE_OUT);
            if(_file_path==null){
                System.err.printf("Missed required <<file_out>> when using %s\n",CMD_FILE_OUT);
                return null;
            }
        }
        if(_file_path!=null){
            File _f = new File(_file_path);
            if(_f.isDirectory()){
                System.err.printf("Specified/target file name(%s) is a directory\n",_file_path);
                return null;
            }
            try {
                FileOutputStream _fos=new FileOutputStream(_f);
                return _fos;
            } catch (Exception wt) {
                System.err.printf("Error, could not create/open the specified file(%s)\n",_file_path);
                wt.printStackTrace(System.err);
                return null;
            }
        }
        return System.out;
    }

    private Main_CLI() {
    }

    public static void main(String args[]) {
        working_args = args;
        if(parse_args()==false){
            return;
        }
        if(shall_show_help()){
            show_help();
            return;
        }
        if(show_in_gui_mode()){
            Main_GUI.main(args);
            return;
        }
        String _type = PARSED_DATA.get(CMD_TYPE_NAME);
        String _package = PARSED_DATA.get(CMD_PACKAGE_NAME);
        boolean _also_as_poolable = PARSED_DATA.containsKey(CMD_ALSO_POOLABLE);
        String _pol_statement = PARSED_DATA.get(CMD_POLICY_STATEMENT);
        try(OutputStream _fous = get_result_out()){
            if(_fous == null){
                return;
            }
            Type_Generator.generate_type(_package, _type,_also_as_poolable, _pol_statement, _fous);
        }catch(Exception wt){
            wt.printStackTrace(System.err);
        }
        
    }

}