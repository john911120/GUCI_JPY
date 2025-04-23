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

### 📋 Secure Coding 点検結果報告（令和7年4月23日時点）
- 本プロジェクトの views ディレクトリに含まれる JSP ファイルについて、Secure Coding の観点から点検を行った結果は以下の通りです。
- 本プロジェクトは、迅速な実装またはデモンストレーションを目的として設計されており、サーバー側でデータをあらかじめ加工し、JSP に渡す構成となっています。
- JSP ファイル内には、ユーザー入力を直接処理するロジックや、動的なユーザー対応の処理はほとんど存在しません。
- そのため、XSS（クロスサイトスクリプティング）やクライアント側の攻撃に対する脆弱性は低く、全体的に安全な状態であることが確認されました。
※ 今後、ユーザー入力を扱う機能を追加する場合は、escapeHtml や c:out などによるエスケープ処理と併せて、入力バリデーションの実装が必要です。

### 📝 ログ構成のセキュリティ改善と移行について（令和7年 4月 23日）
本プロジェクトでは、過去に log4j 1.2.17 および slf4j-log4j12 を使用してログを出力しておりましたが、
これらのコンポーネントにおける重大な脆弱性（例：CVE-2021-44228 など）に対応するため、以下のような対策と構成変更を行いました。

✅ 対応内容
log4j, slf4j-log4j12, log4jdbc-log4j2-jdbc4 の依存関係を完全に削除

ログ実装を logback-classic に変更（SLF4Jに完全対応）

SQLログ用に log4jdbc-remix（SLF4Jベース）を導入

logback.xml にて詳細なSQLログ出力（コンソールおよびファイル）を設定

旧設定ファイル log4j.xml を削除済み

📦 新しいログ構成

コンポーネント	用途
slf4j-api	ログインタフェース
logback-classic	ログ実装（安全で拡張性あり）
log4jdbc-remix	SQL文のログ出力（実行時間含む）
logback.xml	ログ出力設定ファイル（src/main/resources/ 配下に配置）
🔐 セキュリティと互換性
この構成変更により、log4jに関連するすべての脆弱性を排除し、
より安全かつ保守しやすいログ環境を実現しました。

###📝 log4jdbc 設定ファイルの整理(令和7年 4月 23日）

log4jdbc-remix の導入に伴い、既存の設定ファイル `log4jdbc.log4j2.properties` を  
`log4jdbc-remix.properties` にリネームしました。

この変更により、log4jdbc-remix における設定ファイルの自動認識が正しく機能し、  
設定の管理やログ出力挙動の一貫性が向上しました。

また、このファイル名の変更は Git 履歴上で rename として記録されており、  
変更経緯が明確に把握できるようになっています。

### 🧪 テストケースの文書化
- 単体テスト（JUnit）は実装されているものの、テスト仕様書やケース定義書の作成には至っておりません。
- 将来的にはQAの観点でのテスト文書化や、自動化によるテスト効率化も視野に入れて取り組みたいと考えております。

### ⚠️ 例外処理の不十分さ
- 一部の機能ではtry-catchを使用していますが、全体的なエラーハンドリングや例外時のユーザーへの対応はまだ不十分です。
- @ControllerAdviceなどを活用したグローバルな例外処理体制を整え、ユーザーフレンドリーなエラーメッセージを表示できるよう改善していきます。

### 🧱 ハードコーディングの存在
- 短期間で成果を出すため、一部のパスや設定値をコード内に直接記述しています。
- 実運用ではapplication.propertiesなど外部設定ファイルによる管理が望ましいと認識しております。


### 🔧 反省ポイント（振り返り）
- テスト用のメールアドレス（test@example.com）をコード内に直接記述していました。

- 実際の開発環境では、application.properties や環境変数から取得するのが一般的です。

- 本プロジェクトは個人用・展示目的であるため、テスト用としてそのまま記述しています。

- セキュリティ面・保守性向上のため、今後は外部ファイルへの移行を検討します。

---

このような課題に対して真摯に向き合い、改善を継続することで、より実践的かつ保守性の高いシステム構築を目指してまいります。

