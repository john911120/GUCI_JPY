package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsAttachVO;
import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsListAttachVO;
import com.guci.domain.GoodsListVO;
import com.guci.domain.GoodsVO;
import com.guci.mapper.GoodsAttachMapper;
import com.guci.mapper.GoodsMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper mapper;

	@Autowired
	private GoodsAttachMapper attachMapper;

	// 商品を登録し、添付ファイルも同時に保存
	@Transactional
	@Override
	public void register_goods(GoodsVO goods) {
		log.info("register..." + goods);
		mapper.insertSelectKey_goods(goods);

		if(goods.getAttachList() == null || goods.getAttachList().size() <= 0) {
			return;
		}

		goods.getAttachList().forEach(attach ->{
			attach.setGdsNo(goods.getGdsNo());
			attach.setGdsName(goods.getGdsName());
			log.info(attach);
			attachMapper.insert(attach);
		});
	}

	// 商品情報（JOIN含む）を取得
	@Override
	public GoodsListVO get_goods(Long gdsNo) {
		log.info("get..."+gdsNo);
		return mapper.read_goods(gdsNo);
	}
	
	// 商品情報（JOINなし）を取得
	@Override
	public GoodsVO get_goods_no_join(Long gdsNo) {
		log.info("get..."+gdsNo);
		return mapper.read_goods_no_join(gdsNo);
	}

	// 商品情報を更新し、添付ファイルも再登録
	@Transactional
	@Override
	public boolean modify_goods(GoodsVO goods) {
		log.info("modify..."+goods);
		attachMapper.deleteAll(goods.getGdsNo());
		boolean modifyResult = mapper.update_goods(goods)==1;

		if(modifyResult && goods.getAttachList() != null && goods.getAttachList().size()>0) {
			goods.getAttachList().forEach(attach->{
				attach.setGdsNo(goods.getGdsNo());
				attachMapper.insert(attach);
			});
		}

		return modifyResult;
	}

	// 商品情報と添付ファイルを削除
	@Transactional
	@Override
	public boolean remove_goods(Long gdsNo) {
		log.info("remove..."+gdsNo);
		attachMapper.deleteAll(gdsNo);
		return mapper.delete_goods(gdsNo)==1;
	}

	// 商品リストを取得
	@Override
	public List<GoodsListVO> getList_goods() {
		log.info("getList...");
		return mapper.getList_goods();
	}

	// 商品カテゴリリストを取得
	@Override
	public List<CategoryVO> category_goods() {
		return mapper.category_goods();
	}

	// 添付ファイルリストを取得
	@Override
	public List<GoodsAttachVO> getAttachList(Long gdsNo) {
		log.info("get Attach list by gdsNo"+gdsNo);
		return attachMapper.findByGdsNo(gdsNo);
	}


	// 各タブごとの商品リストを取得（ページングあり）
	@Override
	public List<GoodsListAttachVO> getList_best(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_best(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_new(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_new(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_man(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_man(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_woman(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_woman(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_unisex(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_unisex(cri);
	}

	// 商品詳細情報を取得し、閲覧数を更新
	// データの整合性を保ち、Dirty Read（ダーティリード）を防ぐために、トランザクション分離レベル READ_COMMITTED を使用しています。
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public GoodsListAttachVO get_goods_detail(Long gdsNo) throws Exception {
		mapper.gdsViews(gdsNo);
		return mapper.read_goods_detail(gdsNo);	}


	// 各カテゴリの総商品数を取得（ページネーション用）
	@Override
	public int getTotal_best(GoodsCriteria1 cri) {
		return mapper.getTotalCount_best(cri);
	}

	@Override
	public int getTotal_new(GoodsCriteria1 cri) {
		return mapper.getTotalCount_new(cri);
	}

	@Override
	public int getTotal_man(GoodsCriteria1 cri) {
		return mapper.getTotalCount_man(cri);
	}

	@Override
	public int getTotal_woman(GoodsCriteria1 cri) {
		return mapper.getTotalCount_woman(cri);
	}

	@Override
	public int getTotal_unisex(GoodsCriteria1 cri) {
		return mapper.getTotalCount_unisex(cri);
	}


	// 検索結果の商品リストを取得
	@Override
	public List<GoodsListAttachVO> getList_search(GoodsCriteria2 cri) {
		return mapper.getListWithPaging_search(cri);
	}
	
	// 検索結果の総件数を取得
	@Override
	public int getTotal_search(GoodsCriteria2 cri) {
		return mapper.getTotalCount_search(cri);
	}

}
