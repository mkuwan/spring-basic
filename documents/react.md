# React Application

## Introduction
### Voltaを使用してNodeをインストール
1. Voltaを使用してバージョン管理できるようにします
2. https://github.com/volta-cli/volta/releases/download/v1.1.1/volta-1.1.1-windows-x86_64.msi　からインストーラをダウンロードしてインストールします。
3. コンソール
```bash
// voltaでnodeをインストール
volta install node@latest
volta install node stable

// インストールされているnodeのリストを取得
volta list node

// インストールされているnodeのバージョンを確認
node -v 
node --version
```

### Reactアプリケーションの作成(TypeScript)
1. コンソール
```bash
// create-react-appでReactアプリケーションを作成
// インストールするフォルダに移動してから実行
npx create-react-app front-app --template typescript
```

### front-appに移動して追加のパッケージをインストール
1. コンソール
```bash
 npm install --save react-router-dom @types/react-router-dom
 npm install @mui/material @emotion/react @emotion/styled
 npm install @fontsource/roboto
```

### Reactアプリケーションの起動
1. コンソール
```bash
 npm run start
```

