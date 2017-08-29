### 基本构成
    依赖freeMark模板引擎生成对应文件
### 使用说明
- 在工程下新建fast-template文件夹

- 创建工程模板类文件

    - 模板文件为freeMark模板语法
    - 扩展名随意
- 打包安装插件   
- alt+insert 快捷键使用
    - 这里生成的为对应.java文件，文件名称为{当前类名称}+{模板名称}.java
### 已经内置可以使用的变量
- package 包路径，当前类所在包路径
- user 当前系统用户名称
- modelName 当前类名称
- fields 当前类字段集合
    - name 字段名称
    - type 字段类型
- className 当前生成模板的类名称