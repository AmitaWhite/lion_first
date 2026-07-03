/**
 * 상품 등록 페이지
 * 화면 참조: _3 (Sell on Paprika | Product Registration)
 * 담당: B - 백성민
 *
 * TODO:
 *  - 지도 API로 거래 위치 설정 (Kakao/Google Maps)
 *  - 임시 저장 기능
 */
"use client";

import { useRouter } from "next/navigation";
import { useRef, useState } from "react";
import api from "@/lib/api";

const CATEGORIES = [
	{ value: "ELECTRONICS", label: "전자기기" },
	{ value: "FASHION", label: "패션/의류" },
	{ value: "HOME", label: "생활/가구" },
	{ value: "KIDS", label: "유아동" },
	{ value: "BOOKS", label: "도서" },
	{ value: "SPORTS", label: "스포츠/레저" },
	{ value: "HOBBIES", label: "취미/게임" },
	{ value: "OTHERS", label: "기타" },
];

const MAX_FILE_COUNT = 10;
const MAX_FILE_SIZE = 5 * 1024 * 1024;

export default function NewProductPage() {
	const router = useRouter();
	const fileInputRef = useRef<HTMLInputElement>(null);
	const [title, setTitle] = useState("");
	const [category, setCategory] = useState("");
	const [price, setPrice] = useState("");
	const [content, setContent] = useState("");
	const [files, setFiles] = useState<File[]>([]);
	const [previews, setPreviews] = useState<string[]>([]);
	const [isLoading, setIsLoading] = useState(false);

	function handleFileChange(e: React.ChangeEvent<HTMLInputElement>) {
		const selected = Array.from(e.target.files ?? []);
		const overSize = selected.filter((f) => f.size > MAX_FILE_SIZE);
		if (overSize.length > 0) {
			alert(`5MB 초과 파일 ${overSize.length}개는 제외됩니다.`);
		}
		const valid = selected.filter((f) => f.size <= MAX_FILE_SIZE);
		const merged = [...files, ...valid].slice(0, MAX_FILE_COUNT);
		if (files.length + valid.length > MAX_FILE_COUNT) {
			alert(`최대 ${MAX_FILE_COUNT}장까지만 등록됩니다.`);
		}
		setFiles(merged);
		setPreviews(merged.map((f) => URL.createObjectURL(f)));
		e.target.value = "";
	}

	function removeFile(index: number) {
		const next = files.filter((_, i) => i !== index);
		setFiles(next);
		setPreviews(next.map((f) => URL.createObjectURL(f)));
	}

	async function handleSubmit() {
		setIsLoading(true);
		try {
			let imgUrls: string[] = [];
			if (files.length > 0) {
				const formData = new FormData();
				files.forEach((f) => formData.append("files", f));
				const uploadRes = await api.post(
					"/api/v1/images/upload/batch",
					formData,
					{
						headers: { "Content-Type": "multipart/form-data" },
					},
				);
				imgUrls = uploadRes.data.data;
			}
			const res = await api.post("/api/v1/posts", {
				title,
				content,
				price: Number(price),
				category: category || null,
				imgUrls,
			});
			if (res.data.success) {
				router.push(`/products/${res.data.data}`);
			}
		} finally {
			setIsLoading(false);
		}
	}

	return (
		<main style={{ padding: 24, maxWidth: 900, margin: "0 auto" }}>
			<section
				style={{
					background: "var(--color-surface-container-lowest)",
					borderRadius: 24,
					padding: 24,
					boxShadow: "var(--shadow-card)",
				}}
			>
				<h1>상품 등록</h1>
				<p
					style={{ color: "var(--color-on-surface-variant)", marginBottom: 24 }}
				>
					상품 정보를 입력하고 이미지를 업로드하면 등록 준비가 완료됩니다.
				</p>

				<div style={{ display: "grid", gap: 20 }}>
					<div style={{ display: "grid", gap: 8 }}>
						<label>상품 제목</label>
						<input
							value={title}
							onChange={(e) => setTitle(e.target.value)}
							style={{
								padding: 14,
								borderRadius: 16,
								border: "1px solid var(--color-outline-variant)",
							}}
							placeholder="예: 빈티지 캐논 AE-1"
						/>
					</div>
					<div style={{ display: "grid", gap: 8 }}>
						<label>카테고리</label>
						<select
							value={category}
							onChange={(e) => setCategory(e.target.value)}
							style={{
								padding: 14,
								borderRadius: 16,
								border: "1px solid var(--color-outline-variant)",
							}}
						>
							<option value="">카테고리 선택</option>
							{CATEGORIES.map((c) => (
								<option key={c.value} value={c.value}>
									{c.label}
								</option>
							))}
						</select>
					</div>
					<div style={{ display: "grid", gap: 8 }}>
						<label>가격</label>
						<input
							type="number"
							value={price}
							onChange={(e) => setPrice(e.target.value)}
							style={{
								padding: 14,
								borderRadius: 16,
								border: "1px solid var(--color-outline-variant)",
							}}
							placeholder="₩0"
						/>
					</div>
					<div style={{ display: "grid", gap: 8 }}>
						<label>상품 설명</label>
						<textarea
							rows={6}
							value={content}
							onChange={(e) => setContent(e.target.value)}
							style={{
								padding: 14,
								borderRadius: 16,
								border: "1px solid var(--color-outline-variant)",
								resize: "vertical",
							}}
							placeholder="상품 상태, 특징, 거래 지역 등을 입력해 주세요."
						/>
					</div>
					<div style={{ display: "grid", gap: 8 }}>
						<label>사진 업로드 (최대 {MAX_FILE_COUNT}장 / 장당 5MB 이하)</label>
						<input
							ref={fileInputRef}
							type="file"
							multiple
							accept="image/*"
							onChange={handleFileChange}
							style={{ display: "none" }}
						/>
						<button
							type="button"
							onClick={() => fileInputRef.current?.click()}
							style={{
								padding: "14px 24px",
								borderRadius: 16,
								background: "rgba(255, 111, 60, 0.12)",
								backdropFilter: "blur(12px)",
								WebkitBackdropFilter: "blur(12px)",
								border: "1px solid rgba(255, 111, 60, 0.3)",
								color: "var(--color-primary)",
								fontWeight: 600,
								cursor: "pointer",
								boxShadow: "0 4px 16px rgba(255, 111, 60, 0.1)",
								textAlign: "left",
							}}
						>
							사진 선택 ({files.length}/{MAX_FILE_COUNT})
						</button>
						{previews.length > 0 && (
							<div
								style={{
									display: "flex",
									gap: 8,
									flexWrap: "wrap",
									marginTop: 8,
								}}
							>
								{previews.map((src, i) => (
									<div key={i} style={{ position: "relative" }}>
										<img
											src={src}
											alt={`미리보기 ${i + 1}`}
											style={{
												width: 80,
												height: 80,
												objectFit: "cover",
												borderRadius: 8,
												border: "1px solid var(--color-outline-variant)",
											}}
										/>
										<button
											type="button"
											onClick={() => removeFile(i)}
											style={{
												position: "absolute",
												top: -6,
												right: -6,
												width: 20,
												height: 20,
												borderRadius: "50%",
												background: "rgba(0,0,0,0.6)",
												color: "white",
												border: "none",
												cursor: "pointer",
												fontSize: 12,
												lineHeight: "20px",
												textAlign: "center",
												padding: 0,
											}}
										>
											×
										</button>
									</div>
								))}
							</div>
						)}
					</div>
					<button
						type="button"
						onClick={handleSubmit}
						disabled={isLoading}
						style={{
							padding: "16px 20px",
							borderRadius: 16,
							background: isLoading
								? "var(--color-outline-variant)"
								: "var(--color-primary)",
							color: "white",
							fontWeight: 700,
							cursor: isLoading ? "not-allowed" : "pointer",
						}}
					>
						{isLoading ? "등록 중..." : "상품 등록하기"}
					</button>
				</div>
			</section>
		</main>
	);
}
