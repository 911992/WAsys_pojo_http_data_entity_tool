# WAsys_pojo_http_data_entity_tool Release Note

repo: https://github.com/911992/WAsys_pojo_http_data_entity_tool  
Author: [911992](https://github.com/911992)  
*(NOTE: following list carries mentionable(not all) changes. For detailed changes, check source code(s))*  

**0.2.7** (Aug 29, 2020) 

0. Changes related to API change of `WAsys_simple_generic_object_pool` version `0.5.7`, and `WAsys_pojo_http_data` version `0.3.3`
1. `Source_Code::Type_Generator`
    * Generated artifact now follows the new changes related to `WAsys_simple_generic_object_pool-v0.5.7` (when asking for poolable obj)
    * Generated artifact now have `static` functions to get the standalone(out-of-pool) object fatory (`get_standalone_obj_factory(void):Object_Factory<{USER_TYPE_NAME}>` , or `get_obj_factory(void):Object_Factory<{USER_TYPE_NAME}>`)
    * Generated artifact now has a `static private` factory variable that holds an instance of the internal pool (`__factory__`)
    * Internal object-factory now is for both poolable/non-poolable generated artifact modes
    * Moved object factory as imports for poolable artifact as essential one
    * Updated the imports, removed import for `Pool_Context`, and added `Generic_Object_Pool` instead
2. `Source_Code::WAsys_Project_Info.java`
    * Updated `PROJECT_VERSION`, and `PROJECT_BUILD` fields
3. Diagrams
    * Update cover media
    * Updated [`fillable_poolable_gen_artifact_class_diagram`](./_docs/_diagrams/fillable_poolable_gen_artifact_class_diagram.svg) (read the dedicated changes [here](./_docs/_diagrams/fillable_poolable_gen_artifact_class_diagram_release_note.md))
    * Updated [`fillable_gen_artifact_class_diagram`](./_docs/_diagrams/fillable_gen_artifact_class_diagram.svg) (read the dedicated changes [here](./_docs/_diagrams/fillable_gen_artifact_class_diagram_release_note.md))
4. Repo
    * Updated `pom.xml` file
        * Changed the artifact to version `0.2.1`
        * Added copyright literal for generated javadoc(plugin)
    * Updated the [graphical interface screenshot](./_docs/_images/graphical_interface_sample.png)

<hr/>

**0.2.1** (Aug 23, 2020) 

0. Changes related to API change of `WAsys_simple_generic_object_pool` version `0.5.1`
1. `Source_Code::Type_Generator`
    * Inline `Factory` class now implements `wasys.lib.java_type_util.reflect.type_sig.Object_Factory`
    * Removed redudant `wasys.lib.generic_object_pool.api.Poolable_Object` import
2. `Source_Code::WAsys_Project_Info.java`
    * Updated `PROJECT_VERSION`, and `PROJECT_BUILD` fields
3. Diagrams
    * Updated [`fillable_poolable_gen_artifact_class_diagram`](./_docs/_diagrams/fillable_poolable_gen_artifact_class_diagram.svg) (read the dedicated changes [here](./_docs/_diagrams/fillable_poolable_gen_artifact_class_diagram_release_note.md))
    * Updated [`fillable_gen_artifact_class_diagram`](./_docs/_diagrams/fillable_gen_artifact_class_diagram.svg) (read the dedicated changes [here](./_docs/_diagrams/fillable_gen_artifact_class_diagram_release_note.md))
4. Repo
    * Updated `pom.xml` file
        * changed the artifact to version `0.2.1`        
    * Updated the [graphical interface screenshot](./_docs/_images/graphical_interface_sample.png)

<hr/>

**Initial Release 0.1.0** (Jul 26, 2020)