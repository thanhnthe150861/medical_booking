// Đợi cho tài liệu HTML được tải xong
document.addEventListener('DOMContentLoaded', function() {
    // Lấy tham chiếu đến các trường input
    const priceMedicalInput = document.getElementById('priceMedical');
    const pricePrescriptionInput = document.getElementById('pricePrescription');
    const totalPriceInput = document.getElementById('totalPrice');

    // Gắn sự kiện "input" vào các trường input
    priceMedicalInput.addEventListener('input', calculateTotal);
    pricePrescriptionInput.addEventListener('input', calculateTotal);

    // Hàm tính tổng
    function calculateTotal() {
        // Lấy giá trị của các trường "priceMedical" và "pricePrescription"
        const priceMedical = parseFloat(priceMedicalInput.value);
        const pricePrescription = parseFloat(pricePrescriptionInput.value);

        // Kiểm tra xem có phải là số hợp lệ không
        if (!isNaN(priceMedical) && !isNaN(pricePrescription)) {
            // Tính tổng giá trị
            const totalPrice = priceMedical + pricePrescription;

            // Gán giá trị cho trường "totalPrice"
            totalPriceInput.value = totalPrice;
        }
    }
});
