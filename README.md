<img src="icon.png" align="right" width="180px"/>

# StyleBuilder


[>> Downloads <<](https://github.com/FabLabsMC/StyleBuilder/releases)

In 20w17a, Style becomes an immutable object. As a result, calling methods to set properties on style becomes expensive as style is not cached; also many of style's method has since been removed by proguard. As a result, we need builder objects that restore the removed functionalities.
