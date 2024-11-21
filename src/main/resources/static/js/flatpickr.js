let maxDate = new Date();
maxDate = maxDate.setMonth(maxDate.getMonth() + 3);

const daysMapping = {
    '日': 0,
    '月': 1,
    '火': 2,
    '水': 3,
    '木': 4,
    '金': 5,
    '土': 6
};

flatpickr('#checkinTimeTemp', {
    mode: "single",
    locale: 'ja',
    minDate: 'today',
    maxDate: maxDate,
    enableTime: true,
    dateFormat: "Y-m-d H:i", // フォーマット
    minTime: startTime, // 営業開始時間
    maxTime: closeTime, // 営業終了時間
    disable: [
        function (date) {
            // restDays配列に含まれる曜日を非選択にする
            for (let i = 0; i < restDays.length; i++) {
                const close = daysMapping[restDays[i]];
                if (close !== undefined && date.getDay() === close) {
                    return true; // 非選択
                }
            }
            return false;
        }
    ]
});