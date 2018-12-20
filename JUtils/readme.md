### 转换工具类 com.jUtils.base.ConvertUtils

| 返回值 | 方法体 | 描述 |
| :------:| :------ | :------ |
| Date | dateToSqlDate(Date) | util date 转换为 sqldate |
| Timestamp | dateToSqlTimestamp(Date) | date 转换为 timestamp |
|  |  |  |
| String | dateToStr(Date, String) | 日期转换为字符串 |
| String | dateToStr(Date, String, String) | 日期转换为指定格式的字符串 |
| java.util.Date | qlTimestampToDate(Timestamp) | timestamp 转换为date |
| java.util.Date | sqlDateToDate(Date) | sql date 转换为 util date |
| java.util.Date | strToDate(String, Date) | 字符串转换日期 |
| java.util.Date | strToDate(String, String, Date) | 字符串转换为指定格式的日期 |
| double | strToDouble(String, double) | String转换为Double |
| float | strToFloat(String, float) | 字符串转换为float |
| int | strToInt(String, int) | 字符串转换为int |
| long | strToLong(String, long) | String转换为long |
| String | strToStr(String, String) | 如果字符串为空则使用默认字符串 |


### 身份证处理类 com.jUtils.base.IdcardValidator

| 返回值 | 方法体 | 描述 |
| :------:| :------ | :------ |
| int[] | converCharToInt(char[]) | 将字符数组转为整型数组 |
| String | getCheckCodeBySum(int) | 将和值与11取模得到余数进行校验码判断 |
| int | getPowerSum(int[]) | 将身份证的每位和对应位的加权因子相乘之后，再得到和值 |
| int | getUserSex(String) | 身份证信息中代表性别的数值 |
| boolean | is18Idcard(String) | 18位身份证号码的基本数字和位数验校 |
| boolean | isDigital(String) | 数字验证 |
| boolean | isValidate18Idcard(String) | 判断18位身份证的合法性 |
| boolean | isValidatedAllIdcard(String) | 验证身份证是否合法 |

### 


