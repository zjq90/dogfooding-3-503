@echo off
chcp 65001
echo ==========================================
echo    酒店管理系统 - 后端启动脚本
echo ==========================================
echo.

REM 检查Java环境
java -version >nul 2>&1
if errorlevel 1 (
    echo [错误] 未检测到Java环境，请先安装JDK 1.8或更高版本
    pause
    exit /b 1
)

echo [信息] Java环境检测通过
echo.

REM 检查Maven
mvn -version >nul 2>&1
if errorlevel 1 (
    echo [警告] 未检测到Maven，尝试使用mvnw...
    if exist "mvnw.cmd" (
        echo [信息] 使用项目自带的Maven Wrapper
        set MVN_CMD=mvnw.cmd
    ) else (
        echo [错误] 未找到Maven，请先安装Maven或添加mvnw
        pause
        exit /b 1
    )
) else (
    set MVN_CMD=mvn
)

echo [信息] 正在编译后端项目...
cd backend
%MVN_CMD% clean compile -q

if errorlevel 1 (
    echo [错误] 编译失败，请检查代码
    pause
    exit /b 1
)

echo [信息] 编译成功，正在启动服务...
echo [信息] 服务将在 http://localhost:8080 启动
echo.
%MVN_CMD% spring-boot:run

pause
