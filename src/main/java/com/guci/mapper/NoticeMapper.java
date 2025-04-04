package com.guci.mapper;

import java.util.List;

import com.guci.domain.Criteria;
import com.guci.domain.NoticeVO;

/*
  お知らせ掲示板に関するデータベース操作を担当するMapperインターフェース
  MyBatisを通じてSQLと連携される
 */
public interface NoticeMapper {

	/*
      全てのお知らせ記事を取得
      @return お知らせリスト
     */
	public List<NoticeVO> getList();
	
	/*
      ページング付きでお知らせ記事を取得
      @param cri ページ番号と表示件数などの条件
      @return ページングされたお知らせリスト
     */
	public List<NoticeVO> getListWithPaging(Criteria cri);

    /*
      お知らせ記事を新規登録
      @param notice 登録する記事
     */
	public void insert(NoticeVO notice);
	
    /*
      登録と同時に生成された主キー（noticeNo）を取得
      @param notice 登録する記事（noticeNoが返される）
     */
	public void insertSelectKey(NoticeVO notice);

    /*
      記事詳細の取得
      @param noticeNo お知らせ番号
      @return 該当記事の詳細情報
     */
	public NoticeVO read(Long noticeNo);

    /*
      記事を削除
      @param noticeNo お知らせ番号
      @return 削除された件数
     */
	public int delete(Long noticeNo);

    /*
      記事を更新
      @param notice 更新する記事
      @return 更新された件数
     */
	public int update(NoticeVO notice);
	
    /*
      総記事数を取得（ページングのため）
      @param cri 検索やフィルターの条件
      @return 該当件数
     */
	public int getTotalCount(Criteria cri);

}
