package com.lqyrmk.emovie.service;

import com.lqyrmk.emovie.entity.Admin;
import com.lqyrmk.emovie.entity.Ratings;
import org.springframework.stereotype.*;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:18
 * @Description: AdminService
 * @Version 1.0.0
 */

public interface AdminService {

    /**
     * @description: 查询管理员是否存在
     * @author: Limo
     * @date: 2023/4/1 17:20
     * @param: [com.lqyrmk.emovie.entity.Admin]
     * @return: boolean
     */
    public boolean existsAdminName(String name);

    /**
     * @description: 新增管理员
     * @author: Limo
     * @date: 2023/4/1 17:19
     * @param: [com.lqyrmk.emovie.entity.Admin]
     * @return: com.lqyrmk.emovie.entity.Admin
     */
    public Admin insertAdmin(Admin admin);
}
