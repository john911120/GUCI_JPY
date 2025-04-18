<h2 align="left"><span style="color: teal;">🛒 GUCI ショッピングモール プロジェクト</span></h2>

<blockquote>
初めてチームで取り組んだプロジェクトであり、多くの学びと成長が得られました。
</blockquote>

<blockquote>
チームで初めて取り組んだWebショッピングモールのプロジェクトです。
Javaをベースにしたバックエンドと、HTML/CSS/JavaScript/JSP/JQueryを使ったフロントエンドで構成されたチーム型Web開発プロジェクトです。
</blockquote>

<h2 align="left"><span style="color:teal;">🛠 使用技術(Tech Stack) </span></h2>
<li> Java8 </li>
<li> Oracle Database </li>
<li> HTML / CSS / JavaScript </li>
<li> JQuery/ Bootstrap3.3.7 + 4 / JSP / Servlet </li>
<li> Spring Framework5 / Spring Security </li>
<li> Apache Tomcat 10.1.39 </li>
<li> JavaMailSender / HtmlEmail / BCryptPasswordEncoder(認証,暗号化)</li>

<h2 align="left"><span style="color:teal;"> WASサーバー実行環境</span></h2>

本プロジェクトは、ローカルの Apache Tomcat 10.1.39 を基盤にテストを実施しています。

<li> Tomcat フォルダを圧縮し、`/server/apache-tomcat-10.1.39.zip` にて提供しています。
<li> `JAVA_HOME` を設定後、`bin/startup.sh`（または `startup.bat`）にて起動可能です。
<li> WAR ファイルは `/webapps/` フォルダに配置してください。

→ WAS サーバーを直接起動し、ローカル環境での動作確認やテストが可能です。


<h2 align="left"><span style="color:teal;">🗂 機能一覧（Main Features）</span></h2> 
<li> ユーザー登録・ログイン機能　</li>
<li> 商品一覧・詳細表示 </li>
<li> カート・購入処理 </li>
<li> お気に入り（찜하기）管理機能 </li>
<li> レビュー・Q&A登録機能 </li>
<li> 管理者ページ（商品登録・修正・削除）</li>

<h3>🖼 ER図</h3>
<div align="center">
  <img src="doc/ERD_設計図_日本語版.png" width="700"/>
</div>

<h2 align="left"><span style="color:teal;"> 👥 チーム構成（Team）</span></h2>
<li> イ ジンヒョン (チームリーダー) : デザイン, データベース, FAQ/商品/アドミン ページ </li>
<li> グォン セッビョル(チーム員) : お問い合わせ, カート/購買ページ </li>
<li> シン ヨンリョル(チーム員) : デザイン,データベース,お問い合わせ, 会員ページ </li>
<li> イ フェイエ(チーム員) : レビュー, カート/購買ページ </li>
<li> イム チャンヒョク(チーム員) : お知らせ, 会員ページ、ログインロジック作成 </li>

<h2 aligh="left"><span style="color:teal;"><strong>🧠 学び・成果（What I Learned）</strong></span></h2>
<li><strong>実際のチーム開発での役割分担や責任感を経験</strong></li>
<li><strong>ER図とテーブル構造の理解がプロジェクト成功に不可欠だと実感</strong></li>
<li><strong>JavaとSQLの連携についての理解が深まった</strong></li>

<h2 aligh="left"><span style="color:teal;"> 📎 その他（Others）</span></h2>
<li> チーム開発期間: 2021年1月 ～ 2021年2月</li>
<li> 提出目的: 日本企業へのポートフォリオ用</li>
<li>限られた時間内では、機能の開発が最優先であったため、一部のパスや設定はハードコーディングされています。</li>
<li>実際のサービス環境では、<code>application.properties</code>などの外部設定を利用し、柔軟なパスおよびファイル管理が必要であることを認識しております。</li>



<h2 aligh="left"><span style="color:teal;">✍️ 使用ライブラリについて（使用JAR）</span></h2>
<p>
本プロジェクトでは Oracle データベースとの接続のため、`lib/ojdbc6_g.jar` をプロジェクト内に直接含めています。  
Maven や Gradle などの依存関係管理ツールを使用しない環境でも、動作確認を容易に行えるようにするための対応です。
</p>
<p>
そのため、初回起動時は追加設定なしで JDBC ドライバが認識され、すぐに動作確認が可能です。
</p>
<p>※ 当プロジェクトに使われているojdbc6_g.jarファイルは
  <strong>others/ojdbc6_g.jar</strong>に格納されています。</p>
<p>✅ メール認証機能</p>
<li> JavaMailSenderを使用し、6桁の認証コードを生成・送信</li>
<li> MimeMessageHelperでHTML形式のメールを送信</li>

<p> 🔐 仮パスワード再設定</p>
<li> ランダムな12文字の仮パスワードを生成</li>
<li> HtmlEmailで送信後、BCryptPasswordEncoderで暗号化してDBに保存</li>
<li> Spring Security の BCryptPasswordEncoder によってパスワードを安全に暗号化しました。</li>

<h2 aligh="left"><span style="color:teal;"><strong> 🙇‍♂️ 作業を通じての気づきと感想</strong></span></h2>
<p>
このプロジェクトのコード整備・日本語コメント追加は、単なる形式的な作業ではなく、全体の構造とロジックを自分自身の言葉で再確認するプロセスでした。
特に移動中にもERDの整合性を確認しながら作業を進めたことは、限られた時間を最大限に活かす自分なりの努力でした。
</p>
<p>
今後、さらに多言語対応やテストコードの追加など、機能拡張を通してより完成度を高めていく予定です。
</p>


## 🛠 反省点と改善の方向性

本プロジェクトは短期間で完成を目指したチーム開発の一環として進行されたため、以下の点において改善の余地があると感じております。今後の開発では、より実務に近い構成・保守性・品質面を意識して取り組んでまいります。

### 🔒 セキュリティ面の強化
- SQLインジェクションやXSS対策などの脆弱性への対応が不足しており、入力値の検証も限定的です。
- 今後はSpring Securityや入力バリデーションを適切に実装し、より安全なサービスへと改善していく所存です。

### 🧪 テストケースの文書化
- 単体テスト（JUnit）は実装されているものの、テスト仕様書やケース定義書の作成には至っておりません。
- 将来的にはQAの観点でのテスト文書化や、自動化によるテスト効率化も視野に入れて取り組みたいと考えております。

### ⚠️ 例外処理の不十分さ
- 一部の機能ではtry-catchを使用していますが、全体的なエラーハンドリングや例外時のユーザーへの対応はまだ不十分です。
- @ControllerAdviceなどを活用したグローバルな例外処理体制を整え、ユーザーフレンドリーなエラーメッセージを表示できるよう改善していきます。

### 🧱 ハードコーディングの存在
- 短期間で成果を出すため、一部のパスや設定値をコード内に直接記述しています。
- 実運用ではapplication.propertiesなど外部設定ファイルによる管理が望ましいと認識しております。

---

このような課題に対して真摯に向き合い、改善を継続することで、より実践的かつ保守性の高いシステム構築を目指してまいります。

