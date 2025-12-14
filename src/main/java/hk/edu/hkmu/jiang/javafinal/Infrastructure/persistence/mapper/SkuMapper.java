package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.SkuPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkuMapper extends BaseMapper<SkuPO> {
}