# Generated Artifact Class Diagram(`Fillable_Object` and `Poolable_Object`) Version History
repo: https://github.com/911992/WAsys_pojo_http_data_entity_tool  
file: [generated artifact class_diagram(`Fillable_Object` and `Poolable_Object`)](./fillable_poolable_gen_artifact_class_diagram.svg)  
Author: [911992](https://github.com/911992)  

**0.2.7** (Aug 29, 2020)

* Removed usage of `Pool_Context` class in *WAsys Generic Object Pool* component, since it's no more
* Added `Generic_Object_Pool` in *WAsys Generic Object Pool*
* Added `__factory__:Object_Factory<{USER_TYPE_NAME}>` `static final` field to generated `{USER_TYPE_NAME}` class
* Field `__pool__` now is type-var as `Object_Pool<{USER_TYPE_NAME}>`
* Added `get_standalone_obj_factory(void):Object_Factory<{USER_TYPE_NAME}>` in generated `{USER_TYPE_NAME}` class
* Added `<__factory__init>:static{}` as static-type init of `__factory__` var
* Added `get_pool():Object_Pool` method to `{USER_TYPE_NAME}` type

<hr/>

**0.2.1** (Aug 23, 2020)

* Changes related to `WAsys_simple_generic_object_pool` API change version `0.5.1`
* Removed `Object_Factory` from *WAsys Generic Object Pool* componenet (as version 0.5.1 of related repo)
* Added dependent *Type Signature* componenet from `wasys::lib::java_type_util`
* Nested generated `Factory` class now implements `Object_Factory<{USER_TYPE_NAME}>`
* Renamed duplicated `H` on-page reference to `I`

<br/>

**0.1.0** (Jul 26, 2020)

* Initial release