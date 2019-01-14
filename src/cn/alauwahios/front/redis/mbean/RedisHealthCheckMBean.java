/**
* COPYRIGHT. Tianya Inc. ALL RIGHTS RESERVED.
* Project: tianya_fw
* Author: luyz  <luyz@staff.tianya.cn>
* Create On: Jul 20, 2016 4:53:46 PM
* Modify On: Jul 20, 2016 4:53:46 PM by <wusy>
*/

package cn.alauwahios.front.redis.mbean;
/**
 * Redis可用性检测的MBean接口
 * @author luyz
 *
 */
public interface RedisHealthCheckMBean extends UsabilityMBean{
    
    /**
     * 
     * 执行Redis可用性检测,返回整形表示的可用性状态
     * 
     * @return 返回字符串表示的可用性状态。1表示可用，其他表示不可用的信息
     */
    public String getState();

}
