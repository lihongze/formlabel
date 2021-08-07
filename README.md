FormLabel
=========
A set of custom annotations is implemented, which can convert the back-end pojo class to the front-end form configuration data format
Contains several custom core tags **@FormField,
@FormGroup, @Rule**, and a **FormResolver** class for label parsing and packaging can realize the conversion of business model classes into front-end recognizable form data structures, which are suitable for managing the conversion scenarios of back-end complex configuration forms

### @FormField (Used to identify a single configuration item)
 * type : form type （text、radio、checkbox）
 * label : form description
 * options : form options

### @FormGroup (Used to identify the embedded configuration group)
 * label : description

### @Rule (Used to identify configuration item verification rules)
 * required : is it required
 * type : field type（etc:number）
 * message : prompt information
 * trigger : trigger config（change、blur）

**Note：**
  The rule tag attribute has not yet been defined to standardize the entity class, and the attributes can be flexibly changed with the front-end requirements

  
