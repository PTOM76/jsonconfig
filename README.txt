1.前提条件
 Javaを導入していること。
2.ビルド
 「Windowsはbuild.bat」でプラグインのコンパイルができます。
 「Linuxはbuild.sh」でプラグインのコンパイルができます。
3.サーバーの起動
 Windowsは「start.bat」でサーバーの起動ができます。
 Linuxは「start.sh」でサーバーの起動ができます。
4.ビルドとサーバーの起動
 Windowsは「debug.bat」で起動ができます。
 その他の端末は各自でつくるか手作業でしてください。
 自動的にビルド済みのファイルを「run/plugins」へコピーします。
5.はじめの作業
 「debug.bat」 , 「pom.xml」 , 「src/main/resources/plugin.yml」を編集する。

Q.ビルド済みのプラグインはどこにありますか？
 A.「 target/(プラグイン名)-(バージョン).jar」のパスに入っています。
