package com.guci.mapper;

import java.util.List;

import com.guci.domain.FaqVO;

/*
  FAQ（よくある質問）に関するデータベース操作を担当するMapperインターフェース
  MyBatisを通じてSQLと連携する
 */
public interface FaqMapper {

    /*
      すべてのFAQ記事を取得します
      @return FAQのリスト
     */
    public List<FaqVO> getList();

    /*
      FAQ記事を新規登録します
      @param faq 登録する記事情報
     */
    public void insert(FaqVO faq);

    /*
      登録と同時に生成された主キー（faqNo）を取得します
      @param faq 主キーがセットされた記事
     */
    public void insertSelectKey(FaqVO faq);

    /*
      FAQ記事の詳細を取得します
      @param faqNo 記事番号
      @return 対象記事の情報
     */
    public FaqVO read(Long faqNo);

    /*
      FAQ記事を削除します
      @param faqNo 記事番号
      @return 削除された件数
     */
    public int delete(Long faqNo);

    /*
      FAQ記事を更新します
      @param faq 更新する記事情報
      @return 更新された件数
     */
    public int update(FaqVO faq);
    
}
