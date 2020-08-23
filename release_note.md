# WAsys_pojo_http_data_entity_tool Release Note

repo: https://github.com/911992/WAsys_pojo_http_data_entity_tool  
Author: [911992](https://github.com/911992)  
*(NOTE: following list carries mentionable(not all) changes. For detailed changes, check source code(s))*  

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