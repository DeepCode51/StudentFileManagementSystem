package ${package.Mapper};

import ${package.Entity}.SFM${entity};
import ${superMapperClassPackage};

/**
*
* ${table.comment!} Mapper 接口
*
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<SFM${entity}>
<#else>
public interface SFM${table.mapperName} extends ${superMapperClass}<SFM${entity}> {

}
</#if>
