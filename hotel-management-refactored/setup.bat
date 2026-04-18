@echo off
chcp 65001
echo ==========================================
echo    酒店管理系统 - 环境配置脚本
echo ==========================================
echo.

echo [步骤1/3] 检查必要环境...
echo.

REM 检查Java
java -version >nul 2>&1
if errorlevel 1 (
    echo [X] Java环境未安装，请安装JDK 1.8+
) else (
    echo [√] Java环境已安装
    java -version 2>&1 | findstr "version"
)
echo.

REM 检查Node.js
node -v >nul 2>&1
if errorlevel 1 (
    echo [X] Node.js环境未安装，请安装Node.js 16+
) else (
    echo [√] Node.js环境已安装: 
    node -v
)
echo.

REM 检查MySQL
echo [提示] 请确保MySQL服务已启动，并创建了jiudian数据库
echo.

echo [步骤2/3] 安装前端依赖...
cd frontend
if exist "node_modules" (
    echo [信息] 依赖已存在，跳过安装
) else (
    echo [信息] 正在安装前端依赖，请稍候...
    npm install
    if errorlevel 1 (
        echo [错误] 前端依赖安装失败
        pause
        exit /b 1
    )
    echo [√] 前端依赖安装完成
)
cd ..
echo.

echo [步骤3/3] 配置完成！
echo.
echo ==========================================
echo 启动方式：
echo   1. 先运行 start-backend.bat 启动后端
echo   2. 再运行 start-frontend.bat 启动前端
echo.
echo 访问地址：
echo   前端: http://localhost:3000
echo   后端: http://localhost:8080
echo ==========================================
echo.

pause
