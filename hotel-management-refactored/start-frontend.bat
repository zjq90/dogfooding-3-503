@echo off
chcp 65001
echo ==========================================
echo    酒店管理系统 - 前端启动脚本
echo ==========================================
echo.

REM 检查Node.js环境
node -v >nul 2>&1
if errorlevel 1 (
    echo [错误] 未检测到Node.js环境，请先安装Node.js 16+
    pause
    exit /b 1
)

echo [信息] Node.js环境检测通过
echo.

cd frontend

REM 检查node_modules是否存在
if not exist "node_modules" (
    echo [信息] 首次运行，正在安装依赖...
    npm install
    if errorlevel 1 (
        echo [错误] 依赖安装失败
        pause
        exit /b 1
    )
)

echo [信息] 正在启动前端开发服务器...
echo [信息] 服务将在 http://localhost:3000 启动
echo.
npm run serve

pause
